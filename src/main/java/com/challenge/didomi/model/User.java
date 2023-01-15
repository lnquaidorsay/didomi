package com.challenge.didomi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifier;

    private String id;
    private String email;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "users_events",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "event_id") })
    private Set<Event> consents = new HashSet<>();

    public User() {
    }

    public void addEvent(Event event) {
        this.consents.add(event);
        event.getUsers().add(this);
    }

    public void removeEvent(int eventId) {
        Event event = this.consents.stream().filter(e -> e.getIdentifiant() == eventId).findFirst().orElse(null);
        if (event != null) {
            this.consents.remove(event);
            event.getUsers().remove(this);
        }
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

    public Set<Event> getConsents() {
        return consents;
    }

    public void setConsents(Set<Event> consents) {
        this.consents = consents;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
