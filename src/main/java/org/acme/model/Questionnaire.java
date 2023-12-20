package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.*;

@Entity
@Table(name = "questionnaire")
@NamedQuery(name = "Questionnaire.findAll", query = "SELECT q FROM Questionnaire q")
@NamedQuery(name = "Questionnaire.findById", query = "SELECT q FROM Questionnaire q WHERE q.questionnaireId = :questionnaireId")
@RolesAllowed("admin")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_id")
    @JsonProperty("questionnaire_id")
    private Integer questionnaireId;

    @Column(name = "company_id", nullable = false)
    @JsonProperty("company_id")
    private Integer companyId;

    @Column(name = "title", nullable = false)
    @JsonProperty("title")
    private String title;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    // Constructors

    public Questionnaire() {
        // Default constructor
    }

    public Questionnaire(Integer questionnaireId, Integer companyId, String title, String description) {
        this.questionnaireId = questionnaireId;
        this.companyId = companyId;
        this.title = title;
        this.description = description;
    }

    // Getters and Setters

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
        return "Questionnaire{" +
                "questionnaireId=" + questionnaireId +
                ", companyId=" + companyId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
