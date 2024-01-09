package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "question_data")
@NamedQuery(name = "QuestionData.findAll", query = "SELECT qd FROM QuestionData qd")
public class QuestionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_data_id")
    @JsonProperty("question_data_id")
    private Integer questionDataId;

    @Column(name = "question_id", nullable = false)
    private Integer questionId;

    @Column(name = "language_code", nullable = false)
    @JsonProperty("language_code")
    private String languageCode;

    @Column(name = "question_text", nullable = false)
    @JsonProperty("question_text")
    private String questionText;

    @Column(name = "question_type", nullable = false)
    @JsonProperty("question_type")
    private String questionType;

    @Column(name = "question_order", nullable = false)
    @JsonProperty("question_order")
    private Integer questionOrder;

    // Constructors

    public QuestionData() {
        // Default constructor
    }

    public QuestionData(Integer questionDataId, Integer questionId, String languageCode, String questionText, String questionType, Integer questionOrder) {
        this.questionDataId = questionDataId;
        this.questionId = questionId;
        this.languageCode = languageCode;
        this.questionText = questionText;
        this.questionType = questionType;
        this.questionOrder = questionOrder;
    }

    // Getters and Setters

    public Integer getQuestionDataId() {
        return questionDataId;
    }

    public void setQuestionDataId(Integer questionDataId) {
        this.questionDataId = questionDataId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }

    // toString method

    @Override
    public String toString() {
        return "QuestionData{" +
                "questionDataId=" + questionDataId +
                ", questionId=" + questionId +
                ", languageCode='" + languageCode + '\'' +
                ", questionText='" + questionText + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionOrder=" + questionOrder +
                '}';
    }
}
