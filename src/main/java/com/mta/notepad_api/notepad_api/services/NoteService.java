package com.mta.notepad_api.notepad_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mta.notepad_api.notepad_api.domain.Note;
import com.mta.notepad_api.notepad_api.dtos.NoteDTO;
import com.mta.notepad_api.notepad_api.dtos.NoteResponseDTO;
import com.mta.notepad_api.notepad_api.entities.NoteEntity;
import com.mta.notepad_api.notepad_api.entities.UserEntity;
import com.mta.notepad_api.notepad_api.repositories.INoteRepository;

@Service
public class NoteService {

    @Autowired
    private INoteRepository iNoteRepository;

    @Transactional
    public NoteEntity createNote(NoteDTO noteDTO, UserEntity userEntity) {

        Note note = noteDTO.asDomainObject();

        // aplly domain logic

        try {

            // then create entity
            NoteEntity noteEntity = NoteEntity.BasedOnDomainNote(note, userEntity);

            return iNoteRepository.save(noteEntity);
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Error to create note", e);
        }

    }

    @Transactional
    public NoteEntity editNote(NoteDTO noteDTO, UserEntity userEntity) {

        NoteEntity newNoteEntity = NoteEntity.FromDTO(noteDTO, userEntity);

        return iNoteRepository.save(newNoteEntity);
    }

    @Transactional
    public void deleteNote(NoteResponseDTO noteResponseDTO) {

        Optional<NoteEntity> optionalNoteEntity = iNoteRepository.findById(noteResponseDTO.getId());

        iNoteRepository.delete(optionalNoteEntity.get());
    }
}
