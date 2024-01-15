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
import org.acme.model.ApiUser;
import org.jboss.logging.Logger;

@Path("/apiuser")
@RolesAllowed("admin")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ApiUserResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(ApiUserResource.class);

    @POST
    @Transactional
    public Response Register(ApiUser user) {
        user.setPassword(BcryptUtil.bcryptHash(user.getPassword()));
        try {
            entityManager.persist(user);
            return Response.ok(user).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }
}
