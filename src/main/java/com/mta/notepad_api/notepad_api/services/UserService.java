package com.mta.notepad_api.notepad_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mta.notepad_api.notepad_api.dtos.UserDTO;
import com.mta.notepad_api.notepad_api.repositories.IUserRepository;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    public void register(UserDTO userDTO) {

        try {

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
