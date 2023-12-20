package org.acme.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.TeamMember;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/teammember")
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

    @POST
    @Transactional
    public Response createTeamMember(TeamMember teamMember) {
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
    @Path("/{teammember_id}")
    @Transactional
    public Response deleteTeamMember(@PathParam("teammember_id") Integer teamMemberId) {
        TeamMember teamMember = entityManager.find(TeamMember.class, teamMemberId);
        if (teamMember != null) {
            entityManager.remove(teamMember);
            return Response.ok().status(204).build();
        } else {
            return Response.ok("TeamMember not found").status(404).build();
        }
    }
}
