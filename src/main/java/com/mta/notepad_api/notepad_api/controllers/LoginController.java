package com.mta.notepad_api.notepad_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mta.notepad_api.notepad_api.dtos.UserDTO;
import com.mta.notepad_api.notepad_api.entities.UserEntity;
import com.mta.notepad_api.notepad_api.services.UserService;
import com.mta.notepad_api.notepad_api.services.ValidationUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationUserService validationUserService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UserEntity userEntity, BindingResult result) {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult result) {

        if (validationUserService.executeValidations(userDTO)) {

            return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userDTO));
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
