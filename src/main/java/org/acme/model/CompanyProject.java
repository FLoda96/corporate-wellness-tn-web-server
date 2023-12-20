package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "companyproject")
@NamedQuery(name = "CompanyProject.findAll", query = "SELECT cp FROM CompanyProject cp")
public class CompanyProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyproject_id")
    @JsonProperty("companyproject_id")
    private Integer companyProjectId;

    @Column(name = "company_id", nullable = false)
    @JsonProperty("company_id")
    private Integer companyId;

    @Column(name = "project_id", nullable = false)
    @JsonProperty("project_id")
    private Integer projectId;

    // Constructors

    public CompanyProject() {
        // Default constructor
    }

    public CompanyProject(Integer companyProjectId, Integer companyId, Integer projectId) {
        this.companyProjectId = companyProjectId;
        this.companyId = companyId;
        this.projectId = projectId;
    }

    // Getters and Setters

    public Integer getCompanyProjectId() {
        return companyProjectId;
    }

    public void setCompanyProjectId(Integer companyProjectId) {
        this.companyProjectId = companyProjectId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    // toString method

    @Override
    public String toString() {
        return "CompanyProject{" +
                "companyProjectId=" + companyProjectId +
                ", companyId=" + companyId +
                ", projectId=" + projectId +
                '}';
    }
}
