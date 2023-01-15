package com.challenge.didomi.service;

import com.challenge.didomi.dto.UserDto;
import com.challenge.didomi.exception.ResourceNotFoundException;
import com.challenge.didomi.exception.UnprocessableEntityException;
public interface UserService {

    public UserDto addUser(UserDto user) throws UnprocessableEntityException;
    public UserDto getUser (int idUser) throws ResourceNotFoundException;
    public void deleteUser(int idUser);
}
