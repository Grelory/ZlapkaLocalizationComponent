package com.codecool.zlapka.localizationcomponent.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EventHeader {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(255)")
    private String id;
    private String name;
    private Date time;
    @Column(name = "localization_id")
    private String localizationId;

    public EventHeader() {}

    public EventHeader(String name, Date time, String localization) {
        this.name = name;
        this.time = time;
        this.localizationId = localization;
    }

    public EventHeader(String id, String name, Date time, String localization) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.localizationId = localization;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getTime() {
        return time;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }
}
