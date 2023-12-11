package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
@Table(name = "ApiUser")
@UserDefinition
public class ApiUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_user_id")
    @JsonProperty("api_user_id")
    private Integer apiUserId;

    @Username
    public String username;
    @Password
    public String password;
    @Roles
    public String role;
    // Empty constructor
    public ApiUser() {
    }

    // Constructor with all parameters
    public ApiUser(Integer apiUserId, String username, String password, String role) {
        this.apiUserId = apiUserId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and setters

    public Integer getApiUserId() {
        return apiUserId;
    }

    public void setApiUserId(Integer apiUserId) {
        this.apiUserId = apiUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // toString method
    @Override
    public String toString() {
        return "ApiUser{" +
                "apiUserId=" + apiUserId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}