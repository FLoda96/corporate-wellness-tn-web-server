package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "profileauth")
@NamedQuery(name = "ProfileAuth.findByEmail", query = "SELECT q FROM ProfileAuth q WHERE q.email = :email")
public class ProfileAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    @JsonProperty("auth_id")
    private Integer authId;

    @Column(name = "user_id", nullable = false)
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "email", nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(name = "hashed_password", length = 255)
    @JsonProperty("hashed_password")
    private String hashedPassword;

    // Constructors

    public ProfileAuth() {
        // Default constructor
    }

    public ProfileAuth(Integer userId, String email, String hashedPassword, String salt) {
        this.userId = userId;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    // Getters and Setters

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    // toString method

    @Override
    public String toString() {
        return "ProfileAuth{" +
                "authId=" + authId +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }
}
