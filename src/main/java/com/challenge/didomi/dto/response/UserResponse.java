package com.challenge.didomi.dto.response;

import com.challenge.didomi.dto.EventDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;


public class UserResponse {
    @JsonIgnore
    private int identifier;

    private String id;
    private String email;
    private Set<EventDto> consents = new HashSet<>();

    public UserResponse() {
    }

    public UserResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<EventDto> getConsents() {
        return consents;
    }

    public void setConsents(Set<EventDto> consents) {
        this.consents = consents;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
