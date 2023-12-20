package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "team")
@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
@NamedQuery(name = "Team.findById", query = "SELECT t FROM Team t WHERE t.teamId = :teamId")

public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    @JsonProperty("team_id")
    private Integer teamId;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "logo_link")
    @JsonProperty("logo_link")
    private String logoLink;

    // Constructors

    public Team() {
        // Default constructor
    }

    public Team(Integer teamId, String name, String description, String logoLink) {
        this.teamId = teamId;
        this.name = name;
        this.description = description;
        this.logoLink = logoLink;
    }

    // Getters and Setters

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    // toString method
}
