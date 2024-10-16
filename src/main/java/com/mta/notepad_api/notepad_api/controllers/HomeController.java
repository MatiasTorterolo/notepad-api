package com.mta.notepad_api.notepad_api.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mta.notepad_api.notepad_api.dtos.NoteDTO;
import com.mta.notepad_api.notepad_api.repositories.INoteRepository;

@RestController
public class HomeController {

    @Autowired
    private INoteRepository iNoteRepository;

    // must be return a List<NoteDto>
    @GetMapping("/home")
    public List<NoteDTO> showAll() {

        return null;
    }
}
