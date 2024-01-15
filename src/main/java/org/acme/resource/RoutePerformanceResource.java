package org.acme.resource;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.RoutePerformance;
import org.jboss.logging.Logger;

@Path("/routeperformance")
@RolesAllowed("user")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class RoutePerformanceResource {
    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(RoutePerformanceResource.class);
    @GET
    @Path("/all")
    public List<RoutePerformance> GetAllPerformances() {
        return entityManager.createNamedQuery("RoutePerformance.findAll", RoutePerformance.class)
                .getResultList();
    }

    @GET
    @Path("/route/{route_id}")
    public List<RoutePerformance> GetPerformanceByRoute(String route_id) {
            return entityManager.createNamedQuery("RoutePerformance.findByRoute", RoutePerformance.class)
                    .setParameter("routeId", route_id).getResultList();
    }

    @GET
    @Path("/user/{user_id}")
    public List<RoutePerformance> GetPerformanceByUser(String user_id) {
            return entityManager.createNamedQuery("RoutePerformance.findByUser", RoutePerformance.class)
                    .setParameter("userId", user_id).getResultList();
    }

    @POST
    @Transactional
    public Response RegisterPerformance(RoutePerformance performance) {
        LOG.info(performance.toString());
        if ((performance.getRouteId() == null) && (performance.getUserId() == null)) {
            throw new WebApplicationException("Missing obligatory parameters", 400);
        }
        try {
            entityManager.persist(performance);
            return Response.ok(performance).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{performance_id}")
    public Response UpdatePerformance(RoutePerformance performance, String performance_id) {
        RoutePerformance routePerformance = null;
        try {
            routePerformance = entityManager.createNamedQuery("RoutePerformance.findById", RoutePerformance.class)
                    .setParameter("performanceId", performance_id).getSingleResult();
        } catch (NoResultException nre) {
            return Response.ok("The selected performance do not exist").status(400).build();
        }
        RoutePerformance.FinalizeResult(routePerformance, performance);
        try {
            entityManager.merge(routePerformance);
            return Response.ok(routePerformance).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }
}
