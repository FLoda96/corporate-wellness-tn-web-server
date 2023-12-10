package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "RoutePerformance")
@NamedQuery(name = "RoutePerformance.findAll", query = "SELECT rp FROM RoutePerformance rp")
@NamedQuery(name = "RoutePerformance.findByRoute", query = "SELECT rp FROM RoutePerformance rp WHERE rp.routeId = :routeId")
@NamedQuery(name = "RoutePerformance.findByUser", query = "SELECT rp FROM RoutePerformance rp WHERE rp.userId = :userId")
@NamedQuery(name = "RoutePerformance.findById", query = "SELECT rp FROM RoutePerformance rp WHERE rp.performanceId = :performanceId")
public class RoutePerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    @JsonProperty("performance_id")
    private Integer performanceId;

    @Column(name = "route_id")
    @JsonProperty("route_id")
    private Integer routeId;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "timestamp_start")
    @JsonProperty("timestamp_start")
    private Timestamp timestampStart;

    @Column(name = "heart_rate_start")
    @JsonProperty("heart_rate_start")
    private BigDecimal heartRateStart;

    @Column(name = "timestamp_end")
    @JsonProperty("timestamp_end")
    private Timestamp timestampEnd;

    @Column(name = "heart_rate_end")
    @JsonProperty("heart_rate_end")
    private BigDecimal heartRateEnd;

    // Constructors

    public RoutePerformance() {
        // Default constructor
    }

    public RoutePerformance(Integer routeId, Integer userId, Timestamp timestampStart, BigDecimal heartRateStart, Timestamp timestampEnd, BigDecimal heartRateEnd) {
        this.routeId = routeId;
        this.userId = userId;
        this.timestampStart = timestampStart;
        this.heartRateStart = heartRateStart;
        this.timestampEnd = timestampEnd;
        this.heartRateEnd = heartRateEnd;
    }

    // Getters and Setters

    public Integer getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(int performanceId) {
        this.performanceId = performanceId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRoute(int routeId) {
        this.routeId = routeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(Timestamp timestampStart) {
        this.timestampStart = timestampStart;
    }

    public BigDecimal getHeartRateStart() {
        return heartRateStart;
    }

    public void setHeartRateStart(BigDecimal heartRateStart) {
        this.heartRateStart = heartRateStart;
    }

    public Timestamp getTimestampEnd() {
        return timestampEnd;
    }

    public void setTimestampEnd(Timestamp timestampEnd) {
        this.timestampEnd = timestampEnd;
    }

    public BigDecimal getHeartRateEnd() {
        return heartRateEnd;
    }

    public void setHeartRateEnd(BigDecimal heartRateEnd) {
        this.heartRateEnd = heartRateEnd;
    }

    // toString method

    @Override
    public String toString() {
        return "RoutePerformance{" +
                "performanceId=" + performanceId +
                ", routeId=" + routeId +
                ", userId=" + userId +
                ", timestampStart=" + timestampStart +
                ", heartRateStart=" + heartRateStart +
                ", timestampEnd=" + timestampEnd +
                ", heartRateEnd=" + heartRateEnd +
                '}';
    }

    public static void FinalizeResult(RoutePerformance result1, RoutePerformance result2) {
        result1.setTimestampEnd(result2.getTimestampEnd());
        result1.setHeartRateEnd(result2.getHeartRateEnd());
    }
}

