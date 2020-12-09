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
    private Date date;
    @Column(name = "localization_id")
    private String localizationId;

    public EventHeader() {}

    public EventHeader(String name, Date date, String localization) {
        this.name = name;
        this.date = date;
        this.localizationId = localization;
    }

    public EventHeader(String id, String name, Date date, String localization) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.localizationId = localization;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }
}
