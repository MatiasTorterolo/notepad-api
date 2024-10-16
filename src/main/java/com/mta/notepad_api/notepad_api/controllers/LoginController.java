package com.mta.notepad_api.notepad_api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mta.notepad_api.notepad_api.dtos.UserDTO;
import com.mta.notepad_api.notepad_api.dtos.UserResponseDTO;
import com.mta.notepad_api.notepad_api.services.UserService;
import com.mta.notepad_api.notepad_api.services.ValidationUserService;

import jakarta.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationUserService validationUserService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO, BindingResult result) {

        Optional<UserResponseDTO> response = validationUserService.authenticateUser(userDTO);

        if (response.isPresent()) {

            // must return jwt token
            return ResponseEntity.status(HttpStatus.OK).body("Hola " + response.get().getUsername() + ", bienvenido");
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult result) {

        if (validationUserService.executeValidations(userDTO)) {

            return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDTO));
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
