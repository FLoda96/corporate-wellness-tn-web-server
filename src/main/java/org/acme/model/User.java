package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("name")
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
    private String height;
    @JsonProperty("weight")
    private String weight;
    @JsonProperty("heartRate")
    private String heartRate;

    public User() {}

    public User(
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname,
            @JsonProperty("email") String email,
            @JsonProperty("nickname") String nickname,
            @JsonProperty("sex") String sex,
            @JsonProperty("height") String height,
            @JsonProperty("weight") String weight,
            @JsonProperty("heartRate") String heartRate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nickname = nickname;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.heartRate = heartRate;
    }

    // Getter and setter methods...

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", heartRate='" + heartRate + '\'' +
                '}';
    }

    // Getter methods
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

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeartRate() {
        return heartRate;
    }

    // Setter methods
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

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }
}
