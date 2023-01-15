package com.challenge.didomi.repository;

import com.challenge.didomi.Utilitaire.EventType;
import com.challenge.didomi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, EventType> {
    @Override
    Optional<Event> findById(EventType eventType);

    @Query("SELECT b FROM Event b JOIN b.users as a WHERE a.identifier=:userId")
    List<Event> getEventByUserIdJpaQuery(@Param("userId") int userId);

    @Query("SELECT b FROM Event b JOIN b.users as a WHERE a.identifier=:userId and b.id=:idEvent")
    Event getEventByIdentifiantJpaQuery(@Param("userId") int userId, @Param("idEvent") EventType idEvent);
}
