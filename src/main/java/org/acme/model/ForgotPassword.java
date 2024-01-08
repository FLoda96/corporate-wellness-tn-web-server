package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "forgotpassword")
@NamedQuery(name = "ForgotPassword.findAll", query = "SELECT f FROM ForgotPassword f")
@NamedQuery(name = "ForgotPassword.findByEmail", query = "SELECT f FROM ForgotPassword f WHERE f.email = :email")

public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forgotpassword_id")
    @JsonProperty("forgotpassword_id")
    private Integer forgotpasswordId;

    @Column(name = "timestamp_request", nullable = false)
    @JsonProperty("timestamp_request")
    private Timestamp timestampRequest;

    @Column(name = "email", nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(name = "unique_code")
    @JsonProperty("unique_code")
    private String uniqueCode;

    // Constructors

    public ForgotPassword() {
        // Default constructor
    }

    public ForgotPassword(Integer forgotpasswordId, Timestamp timestampRequest, String email, String uniqueCode) {
        this.forgotpasswordId = forgotpasswordId;
        this.timestampRequest = timestampRequest;
        this.email = email;
        this.uniqueCode = uniqueCode;
    }

    // Getters and Setters

    public Integer getForgotpasswordId() {
        return forgotpasswordId;
    }

    public void setForgotpasswordId(Integer forgotpasswordId) {
        this.forgotpasswordId = forgotpasswordId;
    }

    public Timestamp getTimestampRequest() {
        return timestampRequest;
    }

    public void setTimestampRequest(Timestamp timestampRequest) {
        this.timestampRequest = timestampRequest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    // toString method
}
