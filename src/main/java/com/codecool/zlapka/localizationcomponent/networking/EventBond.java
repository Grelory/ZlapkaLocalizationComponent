package com.codecool.zlapka.localizationcomponent.networking;

public class EventBond {

    private final String eventId;
    private final String localizationId;

    public EventBond(String eventId, String localizationId) {
        this.eventId = eventId;
        this.localizationId = localizationId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getLocalizationId() {
        return localizationId;
    }
}
