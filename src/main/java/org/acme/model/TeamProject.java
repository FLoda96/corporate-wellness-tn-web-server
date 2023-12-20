package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "teamproject")
@NamedQuery(name = "TeamProject.findAll", query = "SELECT tp FROM TeamProject tp")
public class TeamProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamproject_id")
    @JsonProperty("teamproject_id")
    private Integer teamProjectId;

    @Column(name = "team_id", nullable = false)
    @JsonProperty("team_id")
    private Integer teamId;

    @Column(name = "project_id", nullable = false)
    @JsonProperty("project_id")
    private Integer projectId;

    // Constructors

    public TeamProject() {
        // Default constructor
    }

    public TeamProject(Integer teamProjectId, Integer teamId, Integer projectId) {
        this.teamProjectId = teamProjectId;
        this.teamId = teamId;
        this.projectId = projectId;
    }

    // Getters and Setters

    public Integer getTeamProjectId() {
        return teamProjectId;
    }

    public void setTeamProjectId(Integer teamProjectId) {
        this.teamProjectId = teamProjectId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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
        return "TeamProject{" +
                "teamProjectId=" + teamProjectId +
                ", teamId=" + teamId +
                ", projectId=" + projectId +
                '}';
    }
}
