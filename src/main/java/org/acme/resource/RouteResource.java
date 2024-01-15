package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Route;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/route")
@RolesAllowed("user")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class RouteResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(RoutePerformanceResource.class);

    @GET
    @Path("/all")
    public List<Route> getAllRoutes() {
        return entityManager.createNamedQuery("Route.findAll", Route.class)
                .getResultList();
    }

    @POST
    @Transactional
    public Response registerRoute(Route route) {
        if ((route.getCompanyId() == null) && (route.getName() == null) && (route.getDescription() == null)) {
            throw new WebApplicationException("Missing obligatory parameters", 400);
        }
        try {
            entityManager.persist(route);
            return Response.ok(route).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }
}
