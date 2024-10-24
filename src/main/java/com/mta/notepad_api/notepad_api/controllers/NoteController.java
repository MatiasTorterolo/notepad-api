package com.mta.notepad_api.notepad_api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mta.notepad_api.notepad_api.dtos.NoteDTO;
import com.mta.notepad_api.notepad_api.dtos.NoteResponseDTO;
import com.mta.notepad_api.notepad_api.entities.UserEntity;
import com.mta.notepad_api.notepad_api.repositories.INoteRepository;
import com.mta.notepad_api.notepad_api.repositories.IUserRepository;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private INoteRepository iNoteRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @GetMapping("/notes/{username}")
    public ResponseEntity<List<NoteResponseDTO>> showAllNotesUser(@PathVariable String username,
            Authentication authentication) {

        Boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        @SuppressWarnings("null")
        String usernameAuthenticated = (String) authentication.getPrincipal();
        Optional<UserEntity> userEntity = iUserRepository.findByUsername(usernameAuthenticated);

        if (userEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!usernameAuthenticated.equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<NoteResponseDTO> listNoteResponseDTO = NoteResponseDTO
                .ToListNoteResponseDTO(iNoteRepository.findAllNotes(userEntity.get().getId()));

        return ResponseEntity.ok(listNoteResponseDTO);

    }

    @PostMapping("/notes/create")
    public ResponseEntity<NoteResponseDTO> crateNote(NoteDTO noteDTO) {
        throw new UnsupportedOperationException("method not yet implemented");
    }

    @PutMapping("/notes/edit/{note_id}")
    public ResponseEntity<NoteResponseDTO> editNote(@PathVariable Long id, NoteResponseDTO noteResponseDTO) {
        throw new UnsupportedOperationException("method not yet implemented");
    }

    @DeleteMapping("/notes/delete/{note_id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        throw new UnsupportedOperationException("method not yet implemented");
    }

}
