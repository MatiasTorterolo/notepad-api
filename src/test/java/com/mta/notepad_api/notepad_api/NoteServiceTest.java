package com.mta.notepad_api.notepad_api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void testCreateNote_WhenIsSavedSuccessfully() {

        NoteDTO noteDTO = new NoteDTO("title", "text", LocalDateTime.now(), LocalDateTime.now());
        UserEntity userEntity = new UserEntity(1L, "Jhon", "jhondoe@gmail.com", "P@kjdddeee123", true);

        NoteEntity expectedNoteEntity = new NoteEntity(3L, noteDTO.getTitle(), noteDTO.getText(),
                noteDTO.getCreationDate(), noteDTO.getLastUpdate(), userEntity);

        Mockito.when(iNoteRepository.save(Mockito.any(NoteEntity.class))).thenReturn(expectedNoteEntity);

        NoteEntity createdNoteEntity = noteService.createNote(noteDTO, userEntity);

        assertNotNull(createdNoteEntity);

        System.out.println("Created Note Entity: " + createdNoteEntity.toString());

        assertEquals(expectedNoteEntity.getId(), createdNoteEntity.getId());

        System.out
                .println("Expect Note: " + expectedNoteEntity.getId() + " Created Note: " + createdNoteEntity.getId());

        assertEquals(expectedNoteEntity.getTitle(), createdNoteEntity.getTitle());
        assertEquals(expectedNoteEntity.getText(), expectedNoteEntity.getText());
        assertEquals(expectedNoteEntity.getCreationDate(), expectedNoteEntity.getCreationDate());
        assertEquals(expectedNoteEntity.getLastUpdate(), expectedNoteEntity.getLastUpdate());
        assertEquals(expectedNoteEntity.getUser(), expectedNoteEntity.getUser());
    }

    @Test
    public void testCreateNote_WhenCanNotSaved() {

    }

}
