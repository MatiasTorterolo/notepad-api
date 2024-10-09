package com.mta.notepad_api.notepad_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mta.notepad_api.notepad_api.entities.UserEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UserEntity userEntity, BindingResult result) {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserEntity userEntity, BindingResult result) {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
