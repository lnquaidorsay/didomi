package com.challenge.didomi.dto;

import com.challenge.didomi.Utilitaire.EventType;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
@JsonFilter("eventDynamicFilter")
public class EventDto {
    @JsonIgnore
    private int identifiant;
    private EventType id;
    private boolean enabled;
    private Set<UserDto> users = new HashSet<>();

    public EventDto() {
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
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

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }
}
