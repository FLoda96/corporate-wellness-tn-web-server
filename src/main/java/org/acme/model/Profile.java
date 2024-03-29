package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDate;

// TO DO : Define constants to build queries with
// TO DO : Define more precise constraints for the fields and validate json fields before doing stuff
// TO DO : Do query parameter validation before
// TO DO : Debate whether to add ON DELETE CASCADE on the dependent records or create a custom function for the deletion of a profile
// TO DO : Account for the first weight/heart_rate value since it's not catched by the update function
// Note that for some reason the name of the class and the name of the table must be the same
@Entity
@Table(name = "Profile")
@NamedQuery(name = "Profile.findAll", query = "SELECT u FROM Profile u")
@NamedQuery(name = "Profile.findByEmail", query = "SELECT u FROM Profile u WHERE u.email = :email")
@NamedQuery(name = "Profile.findById", query = "SELECT u FROM Profile u WHERE u.user_id = :user_id")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer user_id;

    @Column(name = "company_id")
    @JsonProperty("company_id")
    private Integer companyId;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "surname")
    @JsonProperty("surname")
    private String surname;

    @Column(name = "date_of_birth")
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "nickname")
    @JsonProperty("nickname")
    private String nickname;

    @Column(name = "sex")
    @JsonProperty("sex")
    private String sex;

    @Column(name = "waistline")
    @JsonProperty("waistline")
    private BigDecimal waistline;
    @Column(name = "height")
    @JsonProperty("height")
    private BigDecimal height;

    @Column(name = "weight")
    @JsonProperty("weight")
    private BigDecimal weight;

    @Column(name = "heart_rate")
    @JsonProperty("heart_rate")
    private BigDecimal heart_rate;

    @Column(name = "step_length")
    @JsonProperty("step_length")
    private BigDecimal stepLength;

    public Profile() {}

    public Profile(
            @JsonProperty("user_id") Integer user_id,
            @JsonProperty("company_id") Integer companyId,
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname,
            @JsonProperty("date_of_birth") LocalDate dateOfBirth,
            @JsonProperty("email") String email,
            @JsonProperty("nickname") String nickname,
            @JsonProperty("sex") String sex,
            @JsonProperty("waistline") BigDecimal waistline,
            @JsonProperty("height") BigDecimal height,
            @JsonProperty("weight") BigDecimal weight,
            @JsonProperty("heart_rate") BigDecimal heart_rate,
            @JsonProperty("step_length") BigDecimal stepLength) {
        this.user_id = user_id;
        this.companyId = companyId;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.nickname = nickname;
        this.sex = sex;
        this.waistline = waistline;
        this.height = height;
        this.weight = weight;
        this.heart_rate = heart_rate;
        this.stepLength = stepLength;
    }

    // Getter and setter methods...

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date of birth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", waistline='" + waistline + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", heart_rate='" + heart_rate + '\'' +
                ", stepLength='" + stepLength + '\'' +
                '}';
    }

    // Getter methods
    public int getUser_id() {
        return user_id;
    }

    public int getCompanyId() {
        return companyId;
    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {return dateOfBirth;}

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSex() {
        return sex;
    }

    public BigDecimal getWaistline() {return waistline;}

    public BigDecimal getHeight() {
        return height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getHeart_rate() {
        return heart_rate;
    }

    public BigDecimal getStepLength() {
        return stepLength;
    }

    // Setter methods
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setWaistline(BigDecimal waistline) {this.waistline = waistline;}

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public void setWeight(BigDecimal weight) { this.weight = weight; }
    public void setHeart_rate(BigDecimal heart_rate) {
        this.heart_rate = heart_rate;
    }

    public void setStepLength(BigDecimal stepLength) { this.stepLength = stepLength; }

    public static void updateProfile(Profile profile1, Profile profile2) {
        profile1.setName(profile2.getName());
        profile1.setSurname(profile2.getSurname());
        profile1.setEmail(profile2.getEmail());
        profile1.setDateOfBirth(profile2.getDateOfBirth());
        profile1.setNickname(profile2.getNickname());
        profile1.setSex(profile2.getSex());
        profile1.setWaistline(profile2.getWaistline());
        profile1.setHeight(profile2.getHeight());
        profile1.setWeight(profile2.getWeight());
        profile1.setHeart_rate(profile2.getHeart_rate());
        profile1.setStepLength(profile2.getStepLength());
    }
}