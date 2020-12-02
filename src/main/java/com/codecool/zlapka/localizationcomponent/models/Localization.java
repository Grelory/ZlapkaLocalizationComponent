package com.codecool.zlapka.localizationcomponent.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "location")
public class Localization {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domain;
    private String name;
    @Column(name = "geo_tag")
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal altitude;

    public Localization() {}

    public Localization(String domain, String name, BigDecimal latitude,
                        BigDecimal longitude, BigDecimal altitude) {
        this.domain = domain;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Localization(Long id, String domain, String name, BigDecimal latitude,
                        BigDecimal longitude, BigDecimal altitude) {
        this.id = id;
        this.domain = domain;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Long getId() {
        return id;
    }

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getAltitude() {
        return altitude;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setAltitude(BigDecimal altitude) {
        this.altitude = altitude;
    }
}
