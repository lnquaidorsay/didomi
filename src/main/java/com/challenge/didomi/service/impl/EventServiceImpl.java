package com.challenge.didomi.service.impl;

import com.challenge.didomi.Utilitaire.EventType;
import com.challenge.didomi.dto.UserDto;
import com.challenge.didomi.dto.request.EventRequest;
import com.challenge.didomi.dto.response.EventResponse;
import com.challenge.didomi.dto.response.UserResponseId;
import com.challenge.didomi.exception.ResourceNotFoundException;
import com.challenge.didomi.model.Event;
import com.challenge.didomi.model.User;
import com.challenge.didomi.repository.EventRepository;
import com.challenge.didomi.repository.UserRepository;
import com.challenge.didomi.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public EventResponse addEvent(int userId, EventRequest eventRequest) throws ResourceNotFoundException {
        ModelMapper modelMapper = new ModelMapper();
        Event event = userRepository.findById(userId).map(user -> {
            // add and create new Event
            Event eventEntity = modelMapper.map(eventRequest, Event.class);
            Event[] arrConsents = user.getConsents().toArray(new Event[user.getConsents().size()]);
            if(user.getConsents().size() > 0) {
                for (Event s : arrConsents) {
                    EventType idEventRequest = eventRequest.getId();
                    if (idEventRequest.equals(s.getId())) {
                        System.out.println("Event type found in the consent list.");
                        eventEntity.setEnabled(eventRequest.isEnabled());
                        Event evtExistant = eventRepository.getEventByIdentifiantJpaQuery(user.getIdentifier(), idEventRequest);
                        evtExistant.setEnabled(eventRequest.isEnabled());
                        return eventRepository.save(evtExistant);
                    }
                }
            }
            user.addEvent(eventEntity);
            return eventRepository.save(eventEntity);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + userId));
        Set<User> users = event.getUsers();
        User[] listUsers = users.toArray(new User[users.size()]);
        User us = listUsers[0];

        UserDto usrDto = modelMapper.map(us, UserDto.class);

        EventResponse evtResponse = new EventResponse(new UserResponseId(us.getId()), usrDto.getConsents());
        return evtResponse;
    }
}
