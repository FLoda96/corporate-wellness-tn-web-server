package org.acme.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.LoginHistory;
import org.acme.model.Profile;
import org.acme.model.ProfileAuth;
import org.jboss.logging.Logger;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static java.time.LocalTime.now;
import java.time.LocalDateTime;


@Path("/login")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class LoginResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(LoginResource.class);

    @POST
    @Transactional
    public Response GetAnswerByUser(ProfileAuth body) {
        ProfileAuth auth = null;
        try {
            auth = entityManager.createNamedQuery("ProfileAuth.findByEmail", ProfileAuth.class)
                    .setParameter("email", body.getEmail())
                    .getSingleResult();
        } catch (NoResultException nre) {
            return Response.ok("The selected user do not exist").status(400).build();
        }
        if (Objects.equals(body.getHashedPassword(), auth.getHashedPassword())) {
            LoginHistory login = new LoginHistory(auth.getUserId(), Timestamp.valueOf(LocalDateTime.now()));
            try {
            entityManager.persist(login);
            return Response.ok("Logged in").status(200).build();
            } catch (PersistenceException pe) {
                return Response.ok("The operation failed").status(500).build();
            }
        } else {
            return Response.ok("Authentication Failed").status(400).build();
        }

    }
}
