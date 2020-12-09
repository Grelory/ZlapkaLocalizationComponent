package com.codecool.zlapka.localizationcomponent.services;

import com.codecool.zlapka.localizationcomponent.models.EventHeader;
import com.codecool.zlapka.localizationcomponent.models.Localization;
import com.codecool.zlapka.localizationcomponent.repositories.EventHeaderRepository;
import com.codecool.zlapka.localizationcomponent.repositories.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventHeaderService {

    @Autowired
    private LocalizationRepository localizationRepository;
    @Autowired
    private EventHeaderRepository eventHeaderRepository;
    @Autowired
    private JsonMapper jsonMapper;

    public String getEventsLocalizationById(String id) {
        Optional<Localization> locOptional = localizationRepository.findById(id);
        if (locOptional.isEmpty()) return "\"{ \"status\":\"400\", \"info\":\"bad info parameter\"\"}";
        Localization localization = locOptional.get();
        return jsonMapper.eventHeadersJsonRepresentation(new ArrayList<>(localization.getEventHeaders()));
    }

    public String bindEventToLocalization(String jsonElement) {
        EventHeader eventHeader = jsonMapper.getEventHeaderFromJson(jsonElement);
        Optional<Localization> locOptional = localizationRepository.findById(eventHeader.getLocalizationId());
        if (locOptional.isEmpty()) return "\"{ \"status\":\"400\", \"info\":\"bad info parameter\"\"}";
        Localization localization = locOptional.get();
        localization.addEventHeader(eventHeader);
        localizationRepository.save(localization);
        return jsonMapper.jsonRepresentation(eventHeader);
    }

    public String deleteById(String id) {
        if (!eventHeaderRepository.existsById(id)) return "\"{ \"status\":\"400\", \"info\":\"bad info parameter\"\"}";
        EventHeader eventHeader = eventHeaderRepository.findById(id).get();
        Optional<Localization> localizationOptional =
                localizationRepository.findById(eventHeader.getLocalizationId());
        if (localizationOptional.isEmpty()) return "\"{ \"status\":\"400\", \"info\":\"bad info parameter\"\"}";
        Localization localization = localizationOptional.get();
        localization.removeEventHeaderById(eventHeader.getId());
        localizationRepository.save(localization);
        eventHeaderRepository.deleteById(eventHeader.getId());
        List<EventHeader> leftEvents = new ArrayList<>();
        eventHeaderRepository.findAll().forEach(leftEvents::add);
        return jsonMapper.eventHeadersJsonRepresentation(leftEvents);
    }

    public String update(String jsonElement) {
        EventHeader eventHeader = jsonMapper.getEventHeaderFromJson(jsonElement);
        if (doesNotExistInDatabase(eventHeader)) return "\"{ \"status\":\"400\", \"info\":\"bad info parameter\"\"}";
        eventHeaderRepository.save(eventHeader);
        return jsonMapper.jsonRepresentation(eventHeader);
    }

    private boolean doesNotExistInDatabase(EventHeader eventHeader) {
        return !eventHeaderRepository.existsByIdAndLocalizationId(
                eventHeader.getId(), eventHeader.getLocalizationId());
    }

}
