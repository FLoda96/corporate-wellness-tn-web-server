package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "project")
@NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
@NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p where p.projectId = :projectId")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    @JsonProperty("project_id")
    private Integer projectId;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "objective")
    @JsonProperty("objective")
    private Integer objective;

    @Column(name = "logo_link")
    @JsonProperty("logo_link")
    private String logoLink;

    // Constructors

    public Project() {
        // Default constructor
    }

    public Project(Integer projectId, String name, String description, Integer objective, String logoLink) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.objective = objective;
        this.logoLink = logoLink;
    }

    // Getters and Setters

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getObjective() {
        return objective;
    }

    public void setObjective(Integer objective) {
        this.objective = objective;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    // toString method
}
