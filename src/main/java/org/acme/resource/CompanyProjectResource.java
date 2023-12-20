package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.CompanyProject;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/companyproject")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@RolesAllowed("admin")
public class CompanyProjectResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(CompanyProjectResource.class);

    @GET
    @Path("/all")
    public List<CompanyProject> getAllCompanyProjects() {
        return entityManager.createNamedQuery("CompanyProject.findAll", CompanyProject.class)
                .getResultList();
    }

    @POST
    @Transactional
    public Response createCompanyProject(CompanyProject companyProject) {
        try {
            entityManager.persist(companyProject);
            return Response.ok(companyProject).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @PUT
    @Transactional
    public Response updateCompanyProject(CompanyProject companyProject) {
        try {
            entityManager.merge(companyProject);
            return Response.ok(companyProject).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @DELETE
    @Path("/{companyproject_id}")
    @Transactional
    public Response deleteCompanyProject(@PathParam("companyproject_id") Integer companyProjectId) {
        CompanyProject companyProject = entityManager.find(CompanyProject.class, companyProjectId);
        if (companyProject != null) {
            entityManager.remove(companyProject);
            return Response.ok().status(204).build();
        } else {
            return Response.ok("CompanyProject not found").status(404).build();
        }
    }
}
