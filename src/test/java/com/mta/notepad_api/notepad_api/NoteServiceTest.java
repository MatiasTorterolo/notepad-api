package com.mta.notepad_api.notepad_api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mta.notepad_api.notepad_api.dtos.NoteDTO;
import com.mta.notepad_api.notepad_api.dtos.NoteResponseDTO;
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

                LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

                NoteDTO noteDTO = new NoteDTO("Hi, im a title created", "Hi, im a text created", now, now);

                UserEntity userEntity = new UserEntity(1L, "Jhon", "jhondoe@gmail.com", "P@kjdddeee123", true);

                NoteEntity expectedNoteEntity = new NoteEntity("Hi, im a title created", "Hi, im a text created",
                                now, now, userEntity);

                Mockito.when(iNoteRepository.save(Mockito.any(NoteEntity.class)))
                                .thenAnswer(invocation -> invocation.getArgument(0));

                NoteEntity createdNoteEntity = noteService.createNote(noteDTO, userEntity);

                assertNotNull("Entity created is null", createdNoteEntity);

                // System.out.println("Created Note Entity: " + createdNoteEntity.toString());

                if (createdNoteEntity != null) {

                        assertAll("check if all expected attributes matchs the created attributes",
                                        () -> assertEquals(expectedNoteEntity.getTitle(), createdNoteEntity.getTitle()),
                                        () -> assertEquals(expectedNoteEntity.getText(), createdNoteEntity.getText()),
                                        () -> assertEquals(expectedNoteEntity.getCreationDate(),
                                                        createdNoteEntity.getCreationDate()),
                                        () -> assertEquals(expectedNoteEntity.getLastUpdate(),
                                                        createdNoteEntity.getLastUpdate()),
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

        @Test
        public void testEditNoteWhenIsSavedSuccessfully() {

                LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

                NoteResponseDTO noteResponseDTOEdited = new NoteResponseDTO(1L, "Hi, im a title edited",
                                "Hi, im a text edited",
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES), now);

                UserEntity userEntity = new UserEntity(1L, "Jhon", "jhondoe@gmail.com", "P@kjdddeee123", true);

                NoteEntity noteEntityWithoutEdition = new NoteEntity(1L, "Hi, im a title", "Hi, im a text",
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES),
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES), userEntity);

                NoteEntity noteEntityExpected = new NoteEntity(1L, "Hi, im a title edited", "Hi, im a text edited",
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES), now,
                                userEntity);

                Mockito.when(iNoteRepository.save(Mockito.any(NoteEntity.class)))
                                .thenAnswer(invocation -> invocation.getArgument(0));

                NoteEntity noteEntityEdited = noteService.editNote(noteResponseDTOEdited, noteEntityWithoutEdition);

                if (noteEntityEdited != null) {

                        assertAll("check if all expected attributes matchs the edited attributes",
                                        () -> assertEquals(noteEntityExpected.getId(), noteEntityEdited.getId()),
                                        () -> assertEquals(noteEntityExpected.getTitle(), noteEntityEdited.getTitle()),
                                        () -> assertEquals(noteEntityExpected.getText(), noteEntityEdited.getText()),
                                        () -> assertEquals(noteEntityExpected.getCreationDate(),
                                                        noteEntityEdited.getCreationDate()),
                                        () -> assertEquals(noteEntityExpected.getLastUpdate(),
                                                        noteEntityEdited.getLastUpdate()),
                                        () -> assertEquals(noteEntityExpected.getUser(), noteEntityEdited.getUser()));
                }

        }

        @Test
        public void testEditNoteWhenCanNotSaved() {

                LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

                NoteResponseDTO noteResponseDTOEdited = new NoteResponseDTO(1L, "Hi, im a title edited",
                                "Hi, im a text edited",
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES), now);

                UserEntity userEntity = new UserEntity(1L, "Jhon", "jhondoe@gmail.com", "P@kjdddeee123", true);

                NoteEntity noteEntityWithoutEdition = new NoteEntity(1L, "Hi, im a title", "Hi, im a text",
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES),
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES),
                                userEntity);

                Mockito.when(iNoteRepository.save(Mockito.any(NoteEntity.class)))
                                .thenThrow(new RuntimeException("Error to edit note"));

                Exception exception = assertThrows(RuntimeException.class, () -> {

                        noteService.editNote(noteResponseDTOEdited, noteEntityWithoutEdition);
                });

                assertEquals("Error to edit note", exception.getMessage());
        }

        @Test
        public void testDeleteNoteWhenIsDeletedSuccessfully() {

                UserEntity userEntity = new UserEntity(1L, "Jhon", "jhondoe@gmail.com", "P@kjdddeee123", true);

                NoteEntity noteEntity = new NoteEntity(1L, "Hi, im a title", "Hi, im a text",
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES),
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES),
                                userEntity);

                iNoteRepository.delete(noteEntity);

                Mockito.verify(iNoteRepository).delete(noteEntity);

                Mockito.when(iNoteRepository.findById(noteEntity.getId()))
                                .thenReturn(Optional.empty());

                Optional<NoteEntity> noteEntityDeleted = iNoteRepository.findById(noteEntity.getId());

                assertFalse("Note should have been deleted but is present", noteEntityDeleted.isPresent());
        }

        @Test
        public void testDeleteNoteWhenCanNotBeDeleted() {

                UserEntity userEntity = new UserEntity(1L, "Jhon", "jhondoe@gmail.com", "P@kjdddeee123", true);

                NoteEntity noteEntity = new NoteEntity(1L, "Hi, im a title", "Hi, im a text",
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES),
                                LocalDateTime.parse("2007-12-03T10:15:30").truncatedTo(ChronoUnit.MINUTES),
                                userEntity);

                Mockito.doThrow(new RuntimeException("Error to delete note")).when(iNoteRepository)
                                .delete(Mockito.any(NoteEntity.class));

                RuntimeException exception = assertThrows(RuntimeException.class, () -> {

                        iNoteRepository.delete(noteEntity);
                });

                assertEquals("Error to delete note", exception.getMessage());

        }

}
