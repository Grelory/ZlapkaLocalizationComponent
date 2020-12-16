package com.codecool.zlapka.localizationcomponent.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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
    @Enumerated(value = EnumType.STRING)
    private LocalizationStatus status;

    @ElementCollection
    private List<String> events;

    public Localization() {}

    public Localization(String domain, String name, double latitude, double longitude, double altitude,
                        String owner, LocalizationStatus status, List<String> events) {
        this.domain = domain;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.owner = owner;
        this.status = status;
        this.events = events;
    }

    public Localization(String id, String domain, String name, double latitude, double longitude, double altitude,
                        String owner, LocalizationStatus status, List<String> events) {
        this.id = id;
        this.domain = domain;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.owner = owner;
        this.status = status;
        this.events = events;
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

    public LocalizationStatus getStatus() {
        return status;
    }

    public List<String> getEvents() {
        return events;
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

    public void setStatus(LocalizationStatus status) {
        this.status = status;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public void addEvent(String eventId) {
        this.events.add(eventId);
    }

    public void removeEvent(String eventId) {
        this.events.removeIf(id -> id.equals(eventId));
    }

    public boolean eventExists(String eventId) {
        return this.events.stream().anyMatch(id -> id.equals(eventId));
    }
}
