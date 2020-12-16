package com.codecool.zlapka.localizationcomponent.Networking;

public class EventBondUpdate {

    private final String eventId;
    private final String oldLocalizationId;
    private final String newLocalizationId;

    public EventBondUpdate(String eventId, String oldLocalizationId, String newLocalizationId) {
        this.eventId = eventId;
        this.oldLocalizationId = oldLocalizationId;
        this.newLocalizationId = newLocalizationId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getOldLocalizationId() {
        return oldLocalizationId;
    }

    public String getNewLocalizationId() {
        return newLocalizationId;
    }
}
