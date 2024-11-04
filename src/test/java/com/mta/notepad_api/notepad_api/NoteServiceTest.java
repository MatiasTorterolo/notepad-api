package com.mta.notepad_api.notepad_api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mta.notepad_api.notepad_api.dtos.NoteDTO;
import com.mta.notepad_api.notepad_api.entities.NoteEntity;
import com.mta.notepad_api.notepad_api.entities.UserEntity;
import com.mta.notepad_api.notepad_api.repositories.INoteRepository;
import com.mta.notepad_api.notepad_api.services.NoteService;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {

    @Mock
    private INoteRepository iNoteRepository;

    @InjectMocks
    private NoteService noteService;

    // initializes mockito annotations
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNoteWhenIsSavedSuccessfully() {

        NoteDTO noteDTO = new NoteDTO("title", "text", LocalDateTime.now(), LocalDateTime.now());
        UserEntity userEntity = new UserEntity(1L, "Jhon", "jhondoe@gmail.com", "P@kjdddeee123", true);

        NoteEntity expectedNoteEntity = new NoteEntity(3L, noteDTO.getTitle(), noteDTO.getText(),
                noteDTO.getCreationDate(), noteDTO.getLastUpdate(), userEntity);

        Mockito.when(iNoteRepository.save(Mockito.any(NoteEntity.class))).thenReturn(expectedNoteEntity);

        NoteEntity createdNoteEntity = noteService.createNote(noteDTO, userEntity);

        // Tengo que hacer todo esto con trues or falses

        assertNotNull("Entity created is null", createdNoteEntity);

        // System.out.println("Created Note Entity: " + createdNoteEntity.toString());

        if (createdNoteEntity != null) {

            assertAll("check if all expected attributes matchs the created attributes",
                    () -> assertEquals(expectedNoteEntity.getId(), createdNoteEntity.getId()),
                    () -> assertEquals(expectedNoteEntity.getTitle(), createdNoteEntity.getTitle()),
                    () -> assertEquals(expectedNoteEntity.getText(), createdNoteEntity.getText()),
                    () -> assertEquals(expectedNoteEntity.getCreationDate(), createdNoteEntity.getCreationDate()),
                    () -> assertEquals(expectedNoteEntity.getLastUpdate(), createdNoteEntity.getLastUpdate()),
                    () -> assertEquals(expectedNoteEntity.getUser(), createdNoteEntity.getUser()));
        }

    }

    @Test
    public void testCreateNoteWhenCanNotSaved() {

        NoteDTO noteDTO = new NoteDTO("title", "text", LocalDateTime.now(), LocalDateTime.now());
        UserEntity userEntity = new UserEntity(1L, "Jhon", "jhondoe@gmail.com", "P@kjdddeee123", true);

        Mockito.when(iNoteRepository.save(Mockito.any(NoteEntity.class)))
                .thenThrow(new RuntimeException("Error to create note"));

        Exception exception = assertThrows(RuntimeException.class, () -> {

            noteService.createNote(noteDTO, userEntity);
        });

        assertEquals("Error to create note", exception.getMessage());
    }

}
