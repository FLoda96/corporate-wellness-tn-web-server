package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;

// TO DO : Define constants to build queries with
// Note that for some reason the name of the class and the name of the table must be the same
@Entity
@Table(name = "Utente")
@NamedQuery(name = "Utente.findAll", query = "SELECT u FROM Utente u")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("user_id")
    private Integer user_id;
    //@JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("height")
    private BigDecimal height;

    @JsonProperty("weight")
    private BigDecimal weight;

    @JsonProperty("heart_rate")
    private BigDecimal heart_rate;

    public Utente() {}

    public Utente(
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname,
            @JsonProperty("email") String email,
            @JsonProperty("nickname") String nickname,
            @JsonProperty("sex") String sex,
            @JsonProperty("height") BigDecimal height,
            @JsonProperty("weight") BigDecimal weight,
            @JsonProperty("heart_rate") BigDecimal heart_rate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nickname = nickname;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.heart_rate = heart_rate;
    }

    // Getter and setter methods...

    @Override
    public String toString() {
        return "Utente{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", heart_rate='" + heart_rate + '\'' +
                '}';
    }

    // Getter methods
    public int getUser_id() {
        return user_id;
    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSex() {
        return sex;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getHeart_rate() {
        return heart_rate;
    }

    // Setter methods
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public void setHeart_rate(BigDecimal heart_rate) {
        this.heart_rate = heart_rate;
    }
}