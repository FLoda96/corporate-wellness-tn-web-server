package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Answer;
import org.jboss.logging.Logger;

import java.util.Comparator;
import java.util.List;

@Path("/answer")
@RolesAllowed("user")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AnswerResource {

    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(AnswerResource.class);

    @GET
    @Path("/lastanswer/{user_id}/{questionnaire_id}")
    public Answer GetAllAnswers(int user_id, int questionnaire_id) {
        List<Answer> answers = null;
        answers =  entityManager.createNamedQuery("Answer.findLastTimeQuestionnaire", Answer.class)
                .setParameter("userId", user_id)
                .setParameter("questionnaireId", questionnaire_id)
                .getResultList();

        answers.sort(Comparator.comparing(Answer::getTimestampAnswer).reversed());

        // Retrieve the first answer (which is now the latest one chronologically)
        Answer lastAnswer = answers.isEmpty() ? null : answers.get(0);

        return lastAnswer;
    }
    /*
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
