package org.acme.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Question;
import org.acme.model.QuestionData;
import org.acme.model.Questionnaire;
import org.acme.model.QuestionnaireData;
import org.jboss.logging.Logger;

import java.util.ArrayList;
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

    @GET
    @Path("/{questionnaire_id}/{language_code}")
    public QuestionnaireData getQuestionnaireData(Integer questionnaire_id, String language_code) {
        try {
            return entityManager.createNamedQuery("QuestionnaireData.getByIdAndLanguage", QuestionnaireData.class)
                    .setParameter("questionnaireId", questionnaire_id)
                    .setParameter("languageCode", language_code)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    // 200 means ok, 204 means that at some point there was a question with no reference
    @GET
    @Path("questionlist/{questionnaire_id}/{language_code}")
    public List<QuestionData> getQuestionList(Integer questionnaire_id, String language_code) {
        List<Question> questions = null;
        try {
            questions = entityManager.createNamedQuery("Question.findByQuestionnaire", Question.class)
                    .setParameter("questionnaireId", questionnaire_id).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
        //LOG.info(questions.toString());
        List<QuestionData> questionDataList = new ArrayList<QuestionData>();
        for (Question question : questions) {
            try {
                QuestionData questiondata = entityManager.createNamedQuery("QuestionData.getByQuestionAndLanguage", QuestionData.class)
                        .setParameter("questionId", question.getQuestionId())
                        .setParameter("languageCode", language_code)
                        .getSingleResult();
                if (questiondata != null) {
                    questionDataList.add(questiondata);
                }
            } catch (NoResultException nre) {
                return null;
            }
        }
        //LOG.info(questionDataList.toString());
        return questionDataList;
    }
}
