package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.TeamMember;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/teammember")
@RolesAllowed("user")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TeamMemberResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(TeamMemberResource.class);

    @GET
    @Path("/all")
    public List<TeamMember> getAllTeamMembers() {
        return entityManager.createNamedQuery("TeamMember.findAll", TeamMember.class)
                .getResultList();
    }
    @GET
    @Path("/team/{team_id}")
    public List<TeamMember> GetTeamMembers(String team_id) {
        try {
            return entityManager.createNamedQuery("TeamMember.findByTeamId", TeamMember.class)
                    .setParameter("teamId", team_id)
                    .getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @GET
    @Path("/user/{user_id}")
    public List<TeamMember> GetTeamJoined(String user_id) {
        try {
            return entityManager.createNamedQuery("TeamMember.findByUserId", TeamMember.class)
                    .setParameter("userId", user_id)
                    .getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }


    @POST
    @Transactional
    public Response createTeamMember(TeamMember teamMember) {
        TeamMember teamMembership = null;
        try {
             teamMembership = entityManager.createNamedQuery("TeamMember.findByUserIdAndTeam", TeamMember.class)
                     .setParameter("teamId", teamMember.getTeamId()).setParameter("userId",teamMember.getUserId()).getSingleResult();
        } catch (NoResultException nre) {
            // Nothing happens, not finding anything it's a normal situation
        }
        if (teamMembership != null) {
            return Response.ok("User already part of the team").status(400).build();
        }

        try {
            entityManager.persist(teamMember);
            return Response.ok(teamMember).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @PUT
    @Transactional
    public Response updateTeamMember(TeamMember teamMember) {
        try {
            entityManager.merge(teamMember);
            return Response.ok(teamMember).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @DELETE
    @Transactional
    public Response deleteTeamMember(TeamMember teamMember) {
        TeamMember teamMembership = null;
        try {
            teamMembership = entityManager.createNamedQuery("TeamMember.findByUserIdAndTeam", TeamMember.class)
                    .setParameter("teamId", teamMember.getTeamId()).setParameter("userId",teamMember.getUserId()).getSingleResult();
        } catch (NoResultException nre) {
            return Response.ok("Record does not exist").status(400).build();
        }
        if (teamMembership != null) {
            entityManager.remove(teamMembership);
            return Response.ok().status(204).build();
        } else {
            return Response.ok("Record does not exist").status(400).build();
        }
    }
}
