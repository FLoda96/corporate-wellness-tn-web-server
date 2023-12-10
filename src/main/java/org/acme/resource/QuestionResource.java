package org.acme.resource;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Question;
import org.acme.model.RoutePerformance;
import org.jboss.logging.Logger;

@Path("/question")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class QuestionResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(QuestionResource.class);
    @GET
    @Path("/all")
    public List<Question> GetAllQuestions() {
        return entityManager.createNamedQuery("Question.findAll", Question.class)
                .getResultList();
    }

    @GET
    @Path("/{questionnaire_id}")
    public List<Question> GetQuestionByQuestionnaire(String questionnaire_id) {
        return entityManager.createNamedQuery("Question.findByQuestionnaire", Question.class)
                .setParameter("questionnaireId", questionnaire_id).getResultList();
    }

    @POST
    @Transactional
    public Response RegisterPerformance(Question question) {
        if ((question.getQuestionnaireId() == null) && (question.getQuestionText() == null)) {
            throw new WebApplicationException("Missing obligatory parameters", 400);
        }
        try {
            entityManager.persist(question);
            return Response.ok(question).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }
}
