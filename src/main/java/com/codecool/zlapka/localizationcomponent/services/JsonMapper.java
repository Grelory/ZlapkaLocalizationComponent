package com.codecool.zlapka.localizationcomponent.services;

import com.codecool.zlapka.localizationcomponent.models.EventHeader;
import com.codecool.zlapka.localizationcomponent.models.Localization;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public String jsonRepresentation(EventHeader eventHeader) {

        return gson.toJson(eventHeader);
    }

    public String eventHeadersJsonRepresentation(List<EventHeader> eventHeaders) {
        return gson.toJson(eventHeaders);
    }

    public EventHeader getEventHeaderFromJson(String jsonElement) {
        return gson.fromJson(jsonElement, EventHeader.class);
    }
}
