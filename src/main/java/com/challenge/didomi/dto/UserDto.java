package com.challenge.didomi.dto;


import java.util.HashSet;
import java.util.Set;

public class UserDto {

    private String id;

    private int identifier;
    private String email;
    private Set<EventDto> consents = new HashSet<>();

    public UserDto() {
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

    public void addEvent(EventDto event) {
        this.consents.add(event);
        event.getUsers().add(this);
    }

    public void removeEvent(int eventId) {
        EventDto event = this.consents.stream().filter(e -> e.getIdentifiant() == eventId).findFirst().orElse(null);
        if (event != null) {
            this.consents.remove(event);
            event.getUsers().remove(this);
        }
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
