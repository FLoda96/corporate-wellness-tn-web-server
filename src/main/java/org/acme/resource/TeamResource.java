package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Team;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/team")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@RolesAllowed("admin")
public class TeamResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(TeamResource.class);

    @GET
    @Path("/all")
    public List<Team> getAllTeams() {
        return entityManager.createNamedQuery("Team.findAll", Team.class)
                .getResultList();
    }

    @GET
    @Path("/id/{team_id}")
    public Team getTeamById(Integer team_id) {
        try {
        return entityManager.createNamedQuery("Team.findById", Team.class)
                .setParameter("teamId", team_id).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    @POST
    @Transactional
    public Response createTeam(Team team) {
        try {
            entityManager.persist(team);
            return Response.ok(team).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @PUT
    @Transactional
    public Response updateTeam(Team team) {
        try {
            entityManager.merge(team);
            return Response.ok(team).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @DELETE
    @Path("/{team_id}")
    @Transactional
    public Response deleteTeam(@PathParam("team_id") Integer teamId) {
        Team team = entityManager.find(Team.class, teamId);
        if (team != null) {
            entityManager.remove(team);
            return Response.ok().status(204).build();
        } else {
            return Response.ok("Team not found").status(404).build();
        }
    }
}