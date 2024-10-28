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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mta.notepad_api.notepad_api.domain.Note;
import com.mta.notepad_api.notepad_api.dtos.NoteDTO;
import com.mta.notepad_api.notepad_api.dtos.NoteResponseDTO;
import com.mta.notepad_api.notepad_api.entities.NoteEntity;
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

    @PostMapping("/notes/{username}")
    public ResponseEntity<NoteResponseDTO> create(@PathVariable String username, @RequestBody NoteDTO noteDTO,
            Authentication authentication) {

        Boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        if (!isAuthenticated) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<UserEntity> userEntityOptional = iUserRepository.findByUsername(username);

        if (userEntityOptional.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Note note = Note.WrittenWith(noteDTO.getTitle(), noteDTO.getText());

        // domain logic

        NoteEntity noteEntity = NoteEntity.BasedOnDomainNote(note, userEntityOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(NoteResponseDTO.ToNoteResponseDTO(iNoteRepository.save(noteEntity)));
    }

    @PutMapping("/notes/{username}/{note_id}")
    public ResponseEntity<?> edit(@PathVariable String username, @PathVariable Long note_id,
            @RequestBody NoteResponseDTO noteResponseDTO, Authentication authentication) {

        Boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        if (!isAuthenticated) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<UserEntity> userEntityOptional = iUserRepository.findByUsername(username);

        if (userEntityOptional.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found User");
        }

        Optional<NoteEntity> noteEntityOptional = iNoteRepository.findById(note_id);

        if (noteEntityOptional.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found Note");
        }

        NoteResponseDTO noteResponseDTOFromEntity = NoteResponseDTO.ToNoteResponseDTO(noteEntityOptional.get());

        if (noteResponseDTO.equals(noteResponseDTOFromEntity)) {

            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

        Note note = Note.WrittenAt(noteResponseDTO.getTitle(), noteResponseDTO.getText(),
                noteResponseDTO.getCreationDate());

        // domain logic

        return ResponseEntity.status(HttpStatus.OK).body(NoteResponseDTO
                .ToNoteResponseDTO(iNoteRepository.save(NoteEntity.UpdateNoteEntity(note, noteEntityOptional.get()))));
    }

    @DeleteMapping("/notes/{username}/{note_id}")
    public ResponseEntity<?> delete(@PathVariable String username, @PathVariable Long note_id,
            Authentication authentication) {

        Boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        if (!isAuthenticated) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<UserEntity> userEntityOptional = iUserRepository.findByUsername(username);

        if (userEntityOptional.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found User");
        }

        Optional<NoteEntity> noteEntityOptional = iNoteRepository.findById(note_id);

        if (noteEntityOptional.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found Note");
        }

        iNoteRepository.delete(noteEntityOptional.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
