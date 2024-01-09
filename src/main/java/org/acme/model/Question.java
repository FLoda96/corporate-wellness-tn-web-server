package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Question")
@NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
@NamedQuery(name = "Question.findByQuestionnaire", query = "SELECT q FROM Question q WHERE q.questionnaireId = :questionnaireId")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    @JsonProperty("question_id")
    private Integer questionId;

    @Column(name = "questionnaire_id")
    @JsonProperty("questionnaire_id")
    private Integer questionnaireId;

    // Constructors

    public Question() {
        // Default constructor
    }

    public Question(Integer questionId, Integer questionnaireId) {
        this.questionId = questionId;
        this.questionnaireId = questionnaireId;
    }

    // Getters and Setters

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }


    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionnaireId=" + questionnaireId +
                '}';
    }
}
