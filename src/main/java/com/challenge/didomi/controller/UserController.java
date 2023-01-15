package com.challenge.didomi.controller;

import com.challenge.didomi.dto.UserDto;
import com.challenge.didomi.dto.request.UserRequest;
import com.challenge.didomi.dto.response.UserResponse;
import com.challenge.didomi.exception.ResourceNotFoundException;
import com.challenge.didomi.exception.UnprocessableEntityException;
import com.challenge.didomi.service.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Crée un nouvel utilisateur avec une liste de consentement vide")
    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) throws UnprocessableEntityException {

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);

        UserDto newUser = userService.addUser(userDto);

        UserResponse newUserResponse = modelMapper.map(newUser, UserResponse.class);

        return new ResponseEntity<UserResponse>(newUserResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Récupère un utilisateur grâce à son ID à condition que celui-ci soit présent en base de donnée")
    @GetMapping("/user/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") int id) throws ResourceNotFoundException {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userInDb = userService.getUser(id);
        UserResponse userInDbResponse = modelMapper.map(userInDb, UserResponse.class);

        SimpleBeanPropertyFilter eventFilter = SimpleBeanPropertyFilter.serializeAllExcept("users","identifiant");
        FilterProvider eventFilterList = new SimpleFilterProvider().addFilter("eventDynamicFilter", eventFilter);
        MappingJacksonValue evtFilterResponse = new MappingJacksonValue(userInDbResponse);
        evtFilterResponse.setFilters(eventFilterList);
        return evtFilterResponse;
    }

    @ApiOperation(value = "Supprime un utilisateur au moyen de son ID !")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
