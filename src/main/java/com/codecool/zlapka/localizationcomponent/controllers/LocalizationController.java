package com.codecool.zlapka.localizationcomponent.controllers;

import com.codecool.zlapka.localizationcomponent.services.EventHeaderService;
import com.codecool.zlapka.localizationcomponent.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocalizationController {

    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private EventHeaderService eventHeaderService;

    @GetMapping(value = "/localization/all")
    public String getAllLocation() {

        // authentication

        // get appropriate information from http

        return localizationService.getAllLocalizations();

        // return proper status and info
    }

    @GetMapping(value = "/localization")
    public String getLocation(@RequestParam(name = "id") String id) {

        // authentication

        // get appropriate information from http

        return localizationService.getLocalizationById(id);

        // return proper status and info
    }

    @DeleteMapping(value = "/localization")
    public String deleteLocation(@RequestParam(name = "id") String id){
        // authentication

        // get appropriate information from http

        return localizationService.deleteLocalizationById(id);

        // return proper status and info
    }

    @PutMapping(value = "/localization")
    public String putEvent(@RequestBody String jsonLocalization){
        // authentication

        // get appropriate information from http

        return localizationService.update(jsonLocalization);

        // return proper status and info
    }

    @PostMapping(value = "/localization")
    public String postEvent(@RequestBody String jsonLocalization){
        // authentication

        // get appropriate information from http

        localizationService.add(jsonLocalization);

        // return proper status and info
        return "{ \"status\":\"200\", \"info\":\"Localization has been updated\"}";
    }

    @GetMapping(value = "/localization/bond/event")
    public String getEventsByLocalizationId(@RequestParam(name = "id") String id) {

        // authentication

        // get appropriate information from http

        return eventHeaderService.getEventsLocalizationById(id);

        // return proper status and info
    }

    @DeleteMapping(value = "/localization/bond/event")
    public String removeEventFromLocationById(@RequestParam(name = "id") String id){
        // authentication

        // get appropriate information from http

        return eventHeaderService.deleteById(id);

        // return proper status and info
    }

    @PutMapping(value = "/localization/bond/event")
    public String putEventHeaders(@RequestBody String jsonEventHeader){
        // authentication

        // get appropriate information from http

        return eventHeaderService.update(jsonEventHeader);

        // return proper status and info
    }

    @PostMapping(value = "/localization/bond/event")
    public String postEventHeader(@RequestBody String jsonEventHeader){
        // authentication

        // get appropriate information from http

        return eventHeaderService.bindEventToLocalization(jsonEventHeader);

        // return proper status and info
    }





}

