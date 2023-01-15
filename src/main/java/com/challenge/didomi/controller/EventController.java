package com.challenge.didomi.controller;


import com.challenge.didomi.dto.request.EventRequest;
import com.challenge.didomi.dto.response.EventResponse;
import com.challenge.didomi.exception.ResourceNotFoundException;
import com.challenge.didomi.repository.EventRepository;
import com.challenge.didomi.repository.UserRepository;
import com.challenge.didomi.service.EventService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/user/{userId}/event")
    public MappingJacksonValue createEvent(@PathVariable(value = "userId") int userId, @RequestBody EventRequest eventRequest) throws ResourceNotFoundException {

        EventResponse evtResponse = eventService.addEvent(userId,eventRequest);

        SimpleBeanPropertyFilter eventFilter = SimpleBeanPropertyFilter.serializeAllExcept("users","identifiant");
        FilterProvider eventFilterList = new SimpleFilterProvider().addFilter("eventDynamicFilter", eventFilter);
        MappingJacksonValue evtFilterResponse = new MappingJacksonValue(evtResponse);
        evtFilterResponse.setFilters(eventFilterList);
        return evtFilterResponse;

    }
}
