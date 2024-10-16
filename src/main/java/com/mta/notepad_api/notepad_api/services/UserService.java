package com.mta.notepad_api.notepad_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mta.notepad_api.notepad_api.domain.User;
import com.mta.notepad_api.notepad_api.dtos.UserDTO;
import com.mta.notepad_api.notepad_api.entities.UserEntity;
import com.mta.notepad_api.notepad_api.repositories.IUserRepository;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity create(UserDTO userDTO) {

        User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), true);

        UserEntity userEntity = new UserEntity(user.getUsername(), user.getEmail(),
                passwordEncoder.encode(user.getPassword()), true);

        return iUserRepository.save(userEntity);
    }

}
