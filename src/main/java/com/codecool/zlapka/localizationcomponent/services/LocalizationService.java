package com.codecool.zlapka.localizationcomponent.services;

import com.codecool.zlapka.localizationcomponent.models.Localization;
import com.codecool.zlapka.localizationcomponent.repositories.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalizationService {

    @Autowired
    private LocalizationRepository repository;
    @Autowired
    private LocalizationJsonMapper jsonMapper;

    public String getLocalizationById(Long id) {
        return jsonMapper.jsonRepresentation(repository.findById(id));
    }

    public Optional<Localization> add(String jsonElement) {
        Optional<Localization> localization = jsonMapper.getLocalizationFromJson(jsonElement);
        if (localization.isEmpty()) return Optional.empty();
        return Optional.of(repository.save(localization.get()));
    }

    public String deleteLocalizationById(Long id) {
        repository.deleteById(id);
        return "{ \"status\":\"200\", \"info\":\"Localization has been deleted\"";
    }

    public String update(String jsonElement) {
        Optional<Localization> localization = jsonMapper.getLocalizationFromJson(jsonElement);
        localization.ifPresent(repository::save);
        return "{ \"status\":\"200\", \"info\":\"Localization has been updated\"";
    }

}
