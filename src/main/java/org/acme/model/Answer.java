package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "answer")
@NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a")
@NamedQuery(name = "Answer.findByUser", query = "SELECT q FROM Answer q WHERE q.userId = :userId")
@NamedQuery(name = "Answer.findByQuestionnaire", query = "SELECT q FROM Answer q WHERE q.questionnaireId = :questionnaireId")
@NamedQuery(name = "Answer.findByQuestion", query = "SELECT q FROM Answer q WHERE q.questionId = :questionId")
@NamedQuery(name = "Answer.findLastTimeQuestionnaire", query = "SELECT q FROM Answer q WHERE q.userId = :userId and q.questionnaireId = :questionnaireId")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    @JsonProperty("answer_id")
    private Integer answerId;

    @Column(name = "user_id", nullable = false)
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "questionnaire_id")
    @JsonProperty("questionnaire_id")
    private Integer questionnaireId;

    @Column(name = "question_id")
    @JsonProperty("question_id")
    private Integer questionId;

    @Column(name = "answer_type")
    @JsonProperty("answer_type")
    private String answerType;

    @Column(name = "language_code")
    @JsonProperty("language_code")
    private String languageCode;

    @Column(name = "answer_numeric")
    @JsonProperty("answer_numeric")
    private Integer answerNumeric;

    @Column(name = "answer")
    @JsonProperty("answer")
    private String answer;

    @Column(name = "timestamp_answer")
    @JsonProperty("timestamp_answer")
    private Timestamp timestampAnswer;

    // Constructors

    public Answer() {
        // Default constructor
    }

    public Answer(Integer answerId, Integer questionnaireId, Integer questionId, Integer answerNumeric, String answerType, String languageCode, String answer, Timestamp timestampAnswer) {
        this.answerId = answerId;
        this.questionnaireId = questionnaireId;
        this.questionId = questionId;
        this.answerType = answerType;
        this.languageCode = languageCode;
        this.answerNumeric = answerNumeric;
        this.answer = answer;
        this.timestampAnswer = timestampAnswer;
    }

    // Getters and Setters

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Integer getAnswerNumeric() {
        return answerNumeric;
    }

    public void setAnswerNumeric(Integer answerNumeric) {
        this.answerNumeric = answerNumeric;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Timestamp getTimestampAnswer() {
        return timestampAnswer;
    }

    public void setTimestampAnswer(Timestamp timestampAnswer) {
        this.timestampAnswer = timestampAnswer;
    }

    // toString method

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", userId=" + userId +
                ", questionnaireId=" + questionnaireId +
                ", questionId=" + questionId +
                ", answerType='" + answerType + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", answer='" + answer + '\'' +
                ", timestampAnswer=" + timestampAnswer +
                '}';
    }
}
