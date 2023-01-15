package com.challenge.didomi.dto.response;

import com.challenge.didomi.dto.EventDto;

import java.util.HashSet;
import java.util.Set;

public class EventResponse {

    private UserResponseId user;
    private Set<EventDto> consents = new HashSet<>();

    public EventResponse() {
    }

    public EventResponse(UserResponseId user, Set<EventDto> consents) {
        this.user = user;
        this.consents = consents;
    }

    public UserResponseId getUser() {
        return user;
    }

    public void setUser(UserResponseId userResponse) {
        this.user = userResponse;
    }

    public Set<EventDto> getConsents() {
        return consents;
    }

    public void setConsents(Set<EventDto> consents) {
        this.consents = consents;
    }
}
