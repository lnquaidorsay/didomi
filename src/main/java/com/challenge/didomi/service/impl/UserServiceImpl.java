package com.challenge.didomi.service.impl;

import com.challenge.didomi.Utilitaire.Utilitaire;
import com.challenge.didomi.dto.UserDto;
import com.challenge.didomi.exception.ResourceNotFoundException;
import com.challenge.didomi.exception.UnprocessableEntityException;
import com.challenge.didomi.model.User;
import com.challenge.didomi.repository.UserRepository;
import com.challenge.didomi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Utilitaire util;

    @Override
    public UserDto addUser(UserDto user) throws UnprocessableEntityException {
        User userBdd = userRepository.findByEmail(user.getEmail());
        if(userBdd != null) {
            throw new UnprocessableEntityException("A user with this email always exists !!!");
        }
        ModelMapper modelMapper = new ModelMapper();
        User userEntity = modelMapper.map(user, User.class);
        userEntity.setId(util.randomStringId());
        User newUser = userRepository.save(userEntity);
        UserDto userDto = modelMapper.map(newUser, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getUser(int idUser) throws ResourceNotFoundException {
        User usrOth = userRepository.findByIdentifier(idUser);
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + idUser));
        ModelMapper modelMap = new ModelMapper();
        UserDto usrDto = modelMap.map(user, UserDto.class);
        return  usrDto;
    }

    @Override
    public void deleteUser(int idUser) {
        userRepository.deleteById(idUser);
    }
}
