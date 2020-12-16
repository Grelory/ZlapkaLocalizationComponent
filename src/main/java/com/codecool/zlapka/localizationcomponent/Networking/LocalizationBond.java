package com.codecool.zlapka.localizationcomponent.Networking;

public class LocalizationBond {

    private final String localizationId;
    private final String name;

    public LocalizationBond(String localizationId, String name) {
        this.localizationId = localizationId;
        this.name = name;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public String getName() {
        return name;
    }
}
