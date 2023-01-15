package com.challenge.didomi.service;

import com.challenge.didomi.dto.request.EventRequest;
import com.challenge.didomi.dto.response.EventResponse;
import com.challenge.didomi.exception.ResourceNotFoundException;

public interface EventService {

    public EventResponse addEvent (int id, EventRequest eventRequest) throws ResourceNotFoundException;
}
