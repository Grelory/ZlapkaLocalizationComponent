package com.codecool.zlapka.localizationcomponent.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "location")
public class Localization {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(255)")
    private String id;
    private String domain;
    private String name;
    private double latitude;
    private double longitude;
    private double altitude;
    private String owner;
    private boolean isPrivate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<EventHeader> eventHeaders;


    public Localization() {}

    public Localization(String domain, String name, double latitude, double longitude, double altitude,
                        String owner, boolean isPrivate, Set<EventHeader> eventHeaders) {
        this.domain = domain;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.owner = owner;
        this.isPrivate = isPrivate;
        this.eventHeaders = eventHeaders;
    }

    public Localization(String id, String domain, String name, double latitude, double longitude, double altitude,
                        String owner, boolean isPrivate, Set<EventHeader> eventHeaders) {
        this.id = id;
        this.domain = domain;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.owner = owner;
        this.isPrivate = isPrivate;
        this.eventHeaders = eventHeaders;
    }

    public String getId() {
        return id;
    }

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public Set<EventHeader> getEventHeaders() {
        return this.eventHeaders;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void addEventHeader(EventHeader eventHeader) {
        this.eventHeaders.add(eventHeader);
    }

    public void removeEventHeaderById(String id) {
        eventHeaders.removeIf(eventHeader -> eventHeader.getId().equals(id));
    }
}
