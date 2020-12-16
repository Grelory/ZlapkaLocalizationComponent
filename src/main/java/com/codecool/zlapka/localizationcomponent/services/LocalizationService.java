package com.codecool.zlapka.localizationcomponent.services;

import com.codecool.zlapka.localizationcomponent.networking.EventBond;
import com.codecool.zlapka.localizationcomponent.networking.EventBondUpdate;
import com.codecool.zlapka.localizationcomponent.networking.LocalizationBond;
import com.codecool.zlapka.localizationcomponent.models.Localization;
import com.codecool.zlapka.localizationcomponent.repositories.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocalizationService {

    @Autowired
    private NetworkService networkService;
    @Autowired
    private LocalizationRepository localizationRepository;
    @Autowired
    private JsonMapper jsonMapper;

    public String getLocalizationById(String id) {
        return jsonMapper.jsonRepresentation(localizationRepository.findById(id));
    }

    public String add(String jsonElement) {
        Optional<Localization> optionalLocalization = jsonMapper.getLocalizationFromJson(jsonElement);
        if (optionalLocalization.isEmpty()) return "{ \"status\":\"400\", \"info\":\"Invalid data!\"}";
        Localization localization = optionalLocalization.get();
        Localization savedLocalization = localizationRepository.save(localization);
        String localizationBondJson = jsonMapper.parseLocalizationBondToJson(
                new LocalizationBond(savedLocalization.getId(), savedLocalization.getName()));
        String userApiResponse = networkService
                .addLocalizationToUser(localizationBondJson, localization.getOwner());
        return "{ \"status\":\"200\", \"info\":\"Localization has been added\"," +
                "\"userApiResponse\":[" + userApiResponse + "]}";
    }

    public String deleteLocalizationById(String id) {
        if (!localizationRepository.existsById(id)) return "{ \"status\":\"400\", \"info\":\"Invalid data!\"}";
        Localization localization = localizationRepository.findById(id).get();
        LocalizationBond localizationBond = new LocalizationBond(id, localization.getName());
        String localizationBondJson = jsonMapper.parseLocalizationBondToJson(localizationBond);
        String userApiResponse = networkService
                .deleteLocalizationFromUser(localizationBondJson, localization.getOwner());

        // todo delete localizations ids from bound events

        localizationRepository.delete(localization);
        return "{ \"status\":\"200\", \"info\":\"Localization has been deleted\"," +
                "\"userApiResponse\":[" + userApiResponse + "]}";
    }

    public String update(String jsonElement) {
        Optional<Localization> optionalLocalization= jsonMapper.getLocalizationFromJson(jsonElement);
        Localization localization = optionalLocalization.get();

        if (!localizationRepository.existsById(localization.getId())) {
            return "{ \"status\":\"400\", \"info\":\"Invalid data!\"}";
        }

        Localization storedLocalization = localizationRepository.findById(localization.getId()).get();

        String userApiResponse = "";

        if (!storedLocalization.getOwner().equals(localization.getOwner())) {
            LocalizationBond localizationBond = new LocalizationBond(localization.getId(), localization.getName());
            String localizationBondJson = jsonMapper.parseLocalizationBondToJson(localizationBond);
            userApiResponse += networkService.deleteLocalizationFromUser(localizationBondJson, storedLocalization.getOwner());
            userApiResponse += ",";
            userApiResponse += networkService.addLocalizationToUser(localizationBondJson, localization.getOwner());
        }

        if (!storedLocalization.getName().equals(localization.getName())) {
            LocalizationBond localizationBond = new LocalizationBond(localization.getId(), localization.getName());
            String localizationBondJson = jsonMapper.parseLocalizationBondToJson(localizationBond);
            if (userApiResponse.length() != 0) userApiResponse += ",";
            userApiResponse += networkService.updateLocalizationNameInUser(localizationBondJson, localization.getOwner());
        }

        localizationRepository.save(localization);

        return "{ \"status\":\"200\", \"info\":\"Localization has been updated\"," +
                "\"userApiResponse\":[" + userApiResponse + "]}";
    }

    public String getAllLocalizations() {
        List<Localization> localizations = new ArrayList<>();
        localizationRepository.findAll().forEach(localizations::add);
        return jsonMapper.jsonRepresentation(localizations);
    }

    public String getEventsLocalizationById(String id) {
        Optional<Localization> optionalLocalization = localizationRepository.findById(id);
        if (optionalLocalization.isEmpty()) return "{ \"status\":\"400\", \"info\":\"Localization does not exist\"}";
        return jsonMapper.parseEventsIdsToJson(optionalLocalization.get().getEvents());
    }

    public String deleteEventInLocalization(String jsonEventBond) {
        EventBond eventBond = jsonMapper.getEventBondFromJson(jsonEventBond);
        if(badInputParameters(eventBond)) return "{ \"status\":\"400\", \"info\":\"Localization does not exist\"}";
        Localization localization = localizationRepository.findById(eventBond.getLocalizationId()).get();
        if(!localization.eventExists(eventBond.getEventId())) return "{ \"status\":\"400\", \"info\":\"Localization does not exist\"}";
        localization.removeEvent(eventBond.getEventId());
        localizationRepository.save(localization);
        return "{ \"status\":\"200\", \"info\":\"Event has been deleted from localization\"}";
    }

    public String updateEventLocalization(String jsonEventBondUpdate) {
        EventBondUpdate eventBondUpdate = jsonMapper.getEventBondUpdateFromJson(jsonEventBondUpdate);
        if (badInputParameters(eventBondUpdate)) return "{ \"status\":\"400\", \"info\":\"Localization does not exist\"}";
        Localization oldLocalization = localizationRepository.findById(eventBondUpdate.getOldLocalizationId()).get();
        if(!oldLocalization.eventExists(eventBondUpdate.getEventId())) return "{ \"status\":\"400\", \"info\":\"Localization does not exist\"}";
        oldLocalization.removeEvent(eventBondUpdate.getEventId());
        Localization newLocalization = localizationRepository.findById(eventBondUpdate.getNewLocalizationId()).get();
        newLocalization.addEvent(eventBondUpdate.getEventId());
        localizationRepository.saveAll(List.of(oldLocalization, newLocalization));
        return "{ \"status\":\"200\", \"info\":\"Event added to localization\"}";
    }

    public String bindEventToLocalization(String jsonEventBond) {
        EventBond eventBond = jsonMapper.getEventBondFromJson(jsonEventBond);
        if (badInputParameters(eventBond)) return "{ \"status\":\"400\", \"info\":\"Localization does not exist\"}";
        Localization localization = localizationRepository.findById(eventBond.getLocalizationId()).get();
        localization.addEvent(eventBond.getEventId());
        localizationRepository.save(localization);
        return "{ \"status\":\"200\", \"info\":\"Event added to localization\"}";
    }

    private boolean badInputParameters(EventBondUpdate eventBondUpdate) {
        if (eventBondUpdate.getEventId() == null ||
                eventBondUpdate.getNewLocalizationId() == null ||
                eventBondUpdate.getOldLocalizationId() == null) {
            return true;
        }
        return !localizationRepository.existsById(eventBondUpdate.getNewLocalizationId()) ||
                !localizationRepository.existsById(eventBondUpdate.getOldLocalizationId());
    }

    private boolean badInputParameters(EventBond eventBond) {
        if (eventBond.getEventId() == null || eventBond.getLocalizationId() == null) return true;
        return !localizationRepository.existsById(eventBond.getLocalizationId());
    }
}
