package org.acme.resource;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Utente;
import org.jboss.logging.Logger;

@Path("/profile")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProfileResource {
    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(ProfileResource.class);
    @GET
    public List<Utente> GetUsers() {
        return entityManager.createNamedQuery("Utente.findAll", Utente.class)
                .getResultList();
    }

    @POST
    @Transactional
    public Response Register(Utente user) {
        LOG.info(user.toString());
        LOG.info(user.getEmail());
        LOG.info(user.getEmail() != null);
        if (user.getEmail() == null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        //LOG.info(user.toString());
        entityManager.persist(user);
        return Response.ok(user).status(201).build();
        // TO DO: Actual Logic
    }
}