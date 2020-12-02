package com.codecool.zlapka.localizationcomponent.services;

import com.codecool.zlapka.localizationcomponent.models.Localization;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalizationJsonMapper {

    private Gson gson = new Gson();

    // todo implement more functionality

    public Optional<Localization> getLocalizationFromJson(String jsonLocalization) {

        // todo create own parser

        Localization localization = gson.fromJson(jsonLocalization, Localization.class);
        return Optional.ofNullable(localization);
    }

    public String jsonRepresentation(Optional<Localization> localization) {

        //todo create own parser

        if (localization.isEmpty()) return "{ \"error\":\"Localization not found\"}";
        return gson.toJson(localization.get());
    }
}
