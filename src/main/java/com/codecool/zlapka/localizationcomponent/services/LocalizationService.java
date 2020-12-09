package com.codecool.zlapka.localizationcomponent.services;

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
    private LocalizationRepository localizationRepository;
    @Autowired
    private JsonMapper jsonMapper;

    public String getLocalizationById(String id) {
        return jsonMapper.jsonRepresentation(localizationRepository.findById(id));
    }

    public Optional<Localization> add(String jsonElement) {
        Optional<Localization> localization = jsonMapper.getLocalizationFromJson(jsonElement);
        if (localization.isEmpty()) return Optional.empty();
        return Optional.of(localizationRepository.save(localization.get()));
    }

    public String deleteLocalizationById(String id) {
        localizationRepository.deleteById(id);
        return "{ \"status\":\"200\", \"info\":\"Localization has been deleted\"";
    }

    public String update(String jsonElement) {
        Optional<Localization> localization = jsonMapper.getLocalizationFromJson(jsonElement);
        localization.ifPresent(localizationRepository::save);
        return "{ \"status\":\"200\", \"info\":\"Localization has been updated\"";
    }

    public String getAllLocalizations() {
        List<Localization> localizations = new ArrayList<>();
        localizationRepository.findAll().forEach(localizations::add);
        return jsonMapper.jsonRepresentation(localizations);
    }

}
