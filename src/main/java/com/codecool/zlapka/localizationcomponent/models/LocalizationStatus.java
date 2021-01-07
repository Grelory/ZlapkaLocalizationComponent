package com.codecool.zlapka.localizationcomponent.models;

public enum LocalizationStatus {

    PRIVATE("PRIVATE"),
    PUBLIC("PUBLIC");

    private final String name;

    LocalizationStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
