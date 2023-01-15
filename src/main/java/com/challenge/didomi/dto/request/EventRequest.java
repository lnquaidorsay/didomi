package com.challenge.didomi.dto.request;

import com.challenge.didomi.Utilitaire.EventType;

public class EventRequest {
    private EventType id;
    private boolean enabled;

    public EventRequest() {
    }

    public EventType getId() {
        return id;
    }

    public void setId(EventType id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
