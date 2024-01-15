package org.acme.resource;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Profile;
import org.acme.model.ProfileAuth;
import org.jboss.logging.Logger;
import java.security.SecureRandom;
import org.mindrot.jbcrypt.BCrypt;


// TO DO : Check that the user do not already exist
@Path("/registration")
@RolesAllowed("user")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class RegistrationResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(RegistrationResource.class);
    @POST
    @Transactional
    public Response Register(ProfileAuth profileAuth) {
        // TO DO : Check that the email match the user
        if (profileAuth.getEmail() == null  || profileAuth.getHashedPassword() == null) {
            throw new WebApplicationException("Missing obligatory parameters", 400);
        }
        profileAuth.setHashedPassword(BCrypt.hashpw(profileAuth.getHashedPassword(), BCrypt.gensalt()));
        LOG.info("Hash generated " + profileAuth.getHashedPassword());
        try {
            entityManager.persist(profileAuth);
            return Response.ok(profileAuth).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @PUT
    @Transactional
    public Response UpdatePassword(ProfileAuth profileAuth) {
        LOG.info(profileAuth.toString());
        // TO DO : Check that the email match the user
        if (profileAuth.getEmail() == null  || profileAuth.getHashedPassword() == null) {
            throw new WebApplicationException("Missing obligatory parameters", 400);
        }
        ProfileAuth dbAuth = null;
        try {
            dbAuth = entityManager.createNamedQuery("ProfileAuth.findByEmail", ProfileAuth.class)
                    .setParameter("email", profileAuth.getEmail())
                    .getSingleResult();
        } catch (NoResultException nre) {
            return Response.ok("The selected user do not exist").status(400).build();
        }
        dbAuth.setHashedPassword(BCrypt.hashpw(profileAuth.getHashedPassword(), BCrypt.gensalt()));
        LOG.info("Hash generated " + profileAuth.getHashedPassword());
        try {
            entityManager.merge(dbAuth);
            return Response.ok(profileAuth).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }
}
