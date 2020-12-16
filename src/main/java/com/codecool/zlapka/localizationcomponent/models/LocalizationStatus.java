package com.codecool.zlapka.localizationcomponent.models;

public enum LocalizationStatus {

    PRIVATE("private"),
    PUBLIC("public");

    private final String name;

    LocalizationStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
