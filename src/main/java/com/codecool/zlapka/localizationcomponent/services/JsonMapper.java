package com.codecool.zlapka.localizationcomponent.services;

import com.codecool.zlapka.localizationcomponent.networking.EventBond;
import com.codecool.zlapka.localizationcomponent.networking.EventBondUpdate;
import com.codecool.zlapka.localizationcomponent.networking.LocalizationBond;
import com.codecool.zlapka.localizationcomponent.models.Localization;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JsonMapper {

    private Gson gson = new Gson();

    // todo implement more functionality

    public Optional<Localization> getLocalizationFromJson(String jsonLocalization) {

        // todo create own parser

        Localization localization = gson.fromJson(jsonLocalization, Localization.class);
        return Optional.ofNullable(localization);
    }

    public String jsonRepresentation(Optional<Localization> localizationOptional) {

        //todo create own parser

        if (localizationOptional.isEmpty()) return "{ \"error\":\"Localization not found\"}";
        Localization localization = localizationOptional.get();

        return gson.toJson(localization);
    }

    public String jsonRepresentation(List<Localization> localizations) {
        if (localizations.isEmpty()) return "{ \"error\":\"Localizations not found\"}";
        return gson.toJson(localizations);
    }

    public String parseEventsIdsToJson(List<String> events) {
        return gson.toJson(events);
    }

    public EventBond getEventBondFromJson(String json) {
        return gson.fromJson(json, EventBond.class);
    }

    public EventBondUpdate getEventBondUpdateFromJson(String json) {
        return gson.fromJson(json, EventBondUpdate.class);
    }

    public String parseLocalizationBondToJson(LocalizationBond localizationBond) {
        return gson.toJson(localizationBond);
    }

}
