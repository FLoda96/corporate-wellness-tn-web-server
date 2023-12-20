package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.TeamProject;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/teamproject")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@RolesAllowed("admin")
public class TeamProjectResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(TeamProjectResource.class);

    @GET
    @Path("/all")
    public List<TeamProject> getAllTeamProjects() {
        return entityManager.createNamedQuery("TeamProject.findAll", TeamProject.class)
                .getResultList();
    }

    @POST
    @Transactional
    public Response createTeamProject(TeamProject teamProject) {
        try {
            entityManager.persist(teamProject);
            return Response.ok(teamProject).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @PUT
    @Transactional
    public Response updateTeamProject(TeamProject teamProject) {
        try {
            entityManager.merge(teamProject);
            return Response.ok(teamProject).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @DELETE
    @Path("/{teamproject_id}")
    @Transactional
    public Response deleteTeamProject(@PathParam("teamproject_id") Integer teamProjectId) {
        TeamProject teamProject = entityManager.find(TeamProject.class, teamProjectId);
        if (teamProject != null) {
            entityManager.remove(teamProject);
            return Response.ok().status(204).build();
        } else {
            return Response.ok("TeamProject not found").status(404).build();
        }
    }
}
