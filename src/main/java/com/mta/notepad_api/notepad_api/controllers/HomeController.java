package com.mta.notepad_api.notepad_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mta.notepad_api.notepad_api.dtos.NoteDTO;

@RestController
public class HomeController {

    // must be return a List<NoteDto>
    @GetMapping("/home")
    public List<NoteDTO> showAll() {

        return null;
    }
}
