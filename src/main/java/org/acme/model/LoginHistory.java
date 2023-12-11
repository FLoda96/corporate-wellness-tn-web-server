package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;

// TO DO : Define how the LoginHistory interacts with saved credential, probably just save a record anyway
@Entity
@Table(name = "LoginHistory")
@NamedQuery(name = "LoginHistory.findAll", query = "SELECT a FROM LoginHistory a")
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    @JsonProperty("login_id")
    private Integer loginId;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "timestamp_login")
    @JsonProperty("timestamp_login")
    private Timestamp timestampLogin;

    // Empty constructor
    public LoginHistory() {
    }

    // Constructor with all parameters
    public LoginHistory(Integer loginId, Integer userId, Timestamp timestampLogin) {
        this.loginId = loginId;
        this.userId = userId;
        this.timestampLogin = timestampLogin;
    }

    public LoginHistory(Integer userId, Timestamp timestampLogin) {
        this.userId = userId;
        this.timestampLogin = timestampLogin;
    }

    // Getters and setters

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getTimestampLogin() {
        return timestampLogin;
    }

    public void setTimestampLogin(Timestamp timestampLogin) {
        this.timestampLogin = timestampLogin;
    }

    // toString method
    @Override
    public String toString() {
        return "LoginHistory{" +
                "loginId=" + loginId +
                ", userId=" + userId +
                ", timestampLogin=" + timestampLogin +
                '}';
    }
}
