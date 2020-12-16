package com.codecool.zlapka.localizationcomponent.controllers;

import com.codecool.zlapka.localizationcomponent.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocalizationController {

    @Autowired
    private LocalizationService localizationService;

    @GetMapping(value = "/localization/all")
    public ResponseEntity<String> getAllLocation() {

        // authentication

        // get appropriate information from http

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-type", "application/json")
                .header("Access-Control-Allow-Origin", "*")
                .body(localizationService.getAllLocalizations());


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
        System.out.println("\n\n###############################################");
        System.out.println("get method");
        return localizationService.getEventsLocalizationById(id);

        // return proper status and info
    }

    @DeleteMapping(value = "/localization/bond/event")
    public String removeEventFromLocationById(@RequestBody String jsonEventHeader){
        // authentication

        // get appropriate information from http
        System.out.println("\n\n###############################################");
        System.out.println("delete method");
        return localizationService.deleteEventInLocalization(jsonEventHeader);

        // return proper status and info
    }

    @PutMapping(value = "/localization/bond/event")
    public String putEventHeaders(@RequestBody String jsonEventHeader){
        // authentication

        // get appropriate information from http
        System.out.println("\n\n###############################################");
        System.out.println("put method");
        return localizationService.updateEventLocalization(jsonEventHeader);

        // return proper status and info
    }

    @PostMapping(value = "/localization/bond/event")
    public String postEventHeader(@RequestBody String jsonEventHeader){
        // authentication

        // get appropriate information from http
        System.out.println("\n\n###############################################");
        System.out.println("Post method");
        return localizationService.bindEventToLocalization(jsonEventHeader);

        // return proper status and info
    }





}

