package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "teammember")
@NamedQuery(name = "TeamMember.findAll", query = "SELECT tm FROM TeamMember tm")
@NamedQuery(name = "TeamMember.findByTeamId", query = "SELECT tm FROM TeamMember tm where tm.teamId = :teamId")
@NamedQuery(name = "TeamMember.findByUserId", query = "SELECT tm FROM TeamMember tm where tm.userId = :userId")
@NamedQuery(name = "TeamMember.findByUserIdAndTeam", query = "SELECT tm FROM TeamMember tm where tm.userId = :userId and tm.teamId = :teamId")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teammember_id")
    @JsonProperty("teammember_id")
    private Integer teamMemberId;

    @Column(name = "team_id", nullable = false)
    @JsonProperty("team_id")
    private Integer teamId;

    @Column(name = "user_id", nullable = false)
    @JsonProperty("user_id")
    private Integer userId;

    // Constructors

    public TeamMember() {
        // Default constructor
    }

    public TeamMember(Integer teamMemberId, Integer teamId, Integer userId) {
        this.teamMemberId = teamMemberId;
        this.teamId = teamId;
        this.userId = userId;
    }

    // Getters and Setters

    public Integer getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(Integer teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // toString method

    @Override
    public String toString() {
        return "TeamMember{" +
                "teamMemberId=" + teamMemberId +
                ", teamId=" + teamId +
                ", userId=" + userId +
                '}';
    }
}
