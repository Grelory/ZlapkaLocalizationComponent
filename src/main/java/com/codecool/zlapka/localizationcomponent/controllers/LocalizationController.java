package com.codecool.zlapka.localizationcomponent.controllers;

import com.codecool.zlapka.localizationcomponent.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocalizationController {

    @Autowired
    private LocalizationService service;

    @GetMapping(value = "/localization")
    public String getLocation(@RequestParam(name = "id") Long id) {

        // todo authentication

        // todo get appropriate information from http

        return service.getLocalizationById(id);

        // todo return proper status and info
    }

    @DeleteMapping(value = "/localization")
    public String deleteLocation(@RequestParam(name = "id") Long id){
        // todo authentication

        // todo get appropriate information from http

        return service.deleteLocalizationById(id);

        // todo return proper status and info
    }

    @PutMapping(value = "/localization")
    public String putEvent(@RequestBody String jsonLocalization){
        // todo authentication

        // todo get appropriate information from http
        return service.update(jsonLocalization);

        // todo return proper status and info
    }

    @PostMapping(value = "/localization")
    public String postEvent(@RequestBody String jsonLocalization){
        // todo authentication

        // todo get appropriate information from http
        service.add(jsonLocalization);

        // todo return proper status and info
        return "{ \"status\":\"200\", \"info\":\"Localization has been updated\"";
    }
}

