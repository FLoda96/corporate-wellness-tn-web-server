package org.acme.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Answer;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/answer")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AnswerResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(AnswerResource.class);

    /*@GET
    @Path("/all")
    public List<Answer> GetAllAnswers() {
        return entityManager.createNamedQuery("Answer.findAll", Answer.class)
                .getResultList();
    }

    @GET
    @Path("/user/{user_id}")
    public List<Answer> GetAnswerByUser(String user_id) {
        return entityManager.createNamedQuery("Answer.findByUser", Answer.class)
                .setParameter("userId", user_id).getResultList();
    }

    @GET
    @Path("/questionnaire/{questionnaire_id}")
    public List<Answer> GetAnswerByQuestionnaire(String questionnaire_id) {
        return entityManager.createNamedQuery("Answer.findByQuestionnaire", Answer.class)
                .setParameter("questionnaireId", questionnaire_id).getResultList();
    }

    @GET
    @Path("/question/{question_id}")
    public List<Answer> GetAnswerByQuestion(String question_id) {
        return entityManager.createNamedQuery("Answer.findByQuestion", Answer.class)
                .setParameter("questionId", question_id).getResultList();
    }*/

    @POST
    @Transactional
    public Response SubmitAnswer(Answer answer) {
        if (answer.getQuestionnaireId() == null
                || answer.getQuestionId() == null || answer.getAnswer() == null
                || answer.getTimestampAnswer() == null) {
            throw new WebApplicationException("Missing obligatory parameters", 400);
        }
        try {
            entityManager.persist(answer);
            return Response.ok(answer).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @POST
    @Transactional
    @Path("/list")
    public Response SubmitAnswerList(Answer[] answerList) {
        for (Answer answer : answerList) {
            if (answer.getQuestionnaireId() == null
                    || answer.getQuestionId() == null || answer.getAnswer() == null
                    || answer.getTimestampAnswer() == null) {
                throw new WebApplicationException("Missing obligatory parameters", 400);
            }
            try {
                entityManager.persist(answer);
            } catch (PersistenceException pe) {
                return Response.ok("The operation failed").status(500).build();
            }
        }
        return Response.ok("List of answer inserted").status(201).build();
    }
}
