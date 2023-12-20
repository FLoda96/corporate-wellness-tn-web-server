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
import org.acme.model.Project;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/project")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@RolesAllowed("admin")
public class ProjectResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(ProjectResource.class);

    @GET
    @Path("/all")
    public List<Project> getAllProjects() {
        return entityManager.createNamedQuery("Project.findAll", Project.class)
                .getResultList();
    }

    @GET
    @Path("/id/{project_id}")
    public Project getProjectById(Integer project_id) {
        try {
        return entityManager.createNamedQuery("Project.findById", Project.class)
                .setParameter("projectId", project_id).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @POST
    @Transactional
    public Response createProject(Project project) {
        try {
            entityManager.persist(project);
            return Response.ok(project).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @PUT
    @Transactional
    public Response updateProject(Project project) {
        try {
            entityManager.merge(project);
            return Response.ok(project).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @DELETE
    @Path("/{project_id}")
    @Transactional
    public Response deleteProject(@PathParam("project_id") Integer projectId) {
        Project project = entityManager.find(Project.class, projectId);
        if (project != null) {
            entityManager.remove(project);
            return Response.ok().status(204).build();
        } else {
            return Response.ok("Project not found").status(404).build();
        }
    }
}
