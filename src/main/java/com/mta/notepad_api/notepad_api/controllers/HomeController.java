package com.mta.notepad_api.notepad_api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mta.notepad_api.notepad_api.dtos.NoteResponseDTO;
import com.mta.notepad_api.notepad_api.entities.UserEntity;
import com.mta.notepad_api.notepad_api.repositories.INoteRepository;
import com.mta.notepad_api.notepad_api.repositories.IUserRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController("/home")
public class HomeController {

    @Autowired
    private INoteRepository iNoteRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> showAll(@AuthenticationPrincipal Authentication authentication,
            HttpServletRequest request) {

        if (authentication != null && authentication.isAuthenticated()) {

            String username = ((User) authentication.getPrincipal()).getUsername();
            Optional<UserEntity> userEntity = iUserRepository.findByUsername(username);
            List<NoteResponseDTO> listNoteResponseDTO = NoteResponseDTO
                    .ToListNoteResponseDTO(iNoteRepository.findAllNotes(userEntity.get().getId()));

            return ResponseEntity.ok(listNoteResponseDTO);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

}
