package org.acme.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Questionnaire;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/questionnaire")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class QuestionnaireResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(QuestionnaireResource.class);

    @GET
    @Path("/all")
    public List<Questionnaire> getAllQuestionnaires() {
        return entityManager.createNamedQuery("Questionnaire.findAll", Questionnaire.class)
                .getResultList();
    }

    @GET
    @Path("/{questionnaire_id}")
    public Questionnaire getQuestionnaireById(Integer questionnaire_id) {

        try {
        return entityManager.createNamedQuery("Questionnaire.findById", Questionnaire.class)
                .setParameter("questionnaireId", questionnaire_id).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @POST
    @Transactional
    public Response createQuestionnaire(Questionnaire questionnaire) {
        try {
            entityManager.persist(questionnaire);
            return Response.ok(questionnaire).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @PUT
    @Transactional
    public Response updateQuestionnaire(Questionnaire questionnaire) {
        try {
            entityManager.merge(questionnaire);
            return Response.ok(questionnaire).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @DELETE
    @Path("/{questionnaire_id}")
    @Transactional
    public Response deleteQuestionnaire(@PathParam("questionnaire_id") Integer questionnaireId) {
        Questionnaire questionnaire = entityManager.find(Questionnaire.class, questionnaireId);
        if (questionnaire != null) {
            entityManager.remove(questionnaire);
            return Response.ok().status(204).build();
        } else {
            return Response.ok("Questionnaire not found").status(404).build();
        }
    }
}
