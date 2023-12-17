package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "route")
@NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    @JsonProperty("route_id")
    private Integer routeId;

    @Column(name = "company_id")
    @JsonProperty("company_id")
    private Integer companyId;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    // Constructors

    public Route() {
        // Default constructor
    }

    public Route(Integer companyId, String name, String description) {
        this.companyId = companyId;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", companyId=" + companyId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
