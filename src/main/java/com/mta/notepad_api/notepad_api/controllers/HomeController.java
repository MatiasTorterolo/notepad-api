package com.mta.notepad_api.notepad_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    // must be return a List<NoteDto>
    @GetMapping
    public List<?> showAll() {

        return null;
    }
}
