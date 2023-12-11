package org.acme.resource;

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

@Path("/registration")
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
        if (profileAuth.getEmail() == null  || profileAuth.getHashedPassword() == null || profileAuth.getSalt() == null) {
            throw new WebApplicationException("Missing obligatory parameters", 400);
        }
        try {
            entityManager.persist(profileAuth);
            return Response.ok(profileAuth).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

}
