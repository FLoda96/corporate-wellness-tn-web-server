package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "questionnaire_data")
@NamedQuery(name = "QuestionnaireData.findAll", query = "SELECT qd FROM QuestionnaireData qd")
@NamedQuery(name = "QuestionnaireData.getByIdAndLanguage", query = "SELECT qd FROM QuestionnaireData qd where qd.questionnaireId = :questionnaireId and qd.languageCode = :languageCode")
public class QuestionnaireData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_data_id")
    @JsonProperty("questionnaire_data_id")
    private Integer questionnaireDataId;

    @Column(name = "questionnaire_id", nullable = false)
    @JsonProperty("questionnaire_id")
    private Integer questionnaireId;

    @Column(name = "language_code", nullable = false)
    @JsonProperty("language_code")
    private String languageCode;

    @Column(name = "title", nullable = false)
    @JsonProperty("title")
    private String title;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    // Constructors

    public QuestionnaireData() {
        // Default constructor
    }

    public QuestionnaireData(Integer questionnaireDataId, Integer questionnaireId, String languageCode, String title, String description) {
        this.questionnaireDataId = questionnaireDataId;
        this.questionnaireId = questionnaireId;
        this.languageCode = languageCode;
        this.title = title;
        this.description = description;
    }

    // Getters and Setters

    public Integer getQuestionnaireDataId() {
        return questionnaireDataId;
    }

    public void setQuestionnaireDataId(Integer questionnaireDataId) {
        this.questionnaireDataId = questionnaireDataId;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method

    @Override
    public String toString() {
        return "QuestionnaireData{" +
                "questionnaireDataId=" + questionnaireDataId +
                ", questionnaireId=" + questionnaireId +
                ", languageCode='" + languageCode + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
