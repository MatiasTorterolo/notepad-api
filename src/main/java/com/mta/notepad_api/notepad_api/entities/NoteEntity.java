package com.mta.notepad_api.notepad_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import com.mta.notepad_api.notepad_api.domain.Note;
import com.mta.notepad_api.notepad_api.dtos.NoteDTO;

@Entity
@Table(name = "notes")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String text;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public NoteEntity() {
    }

    public NoteEntity(String title, String text, LocalDateTime creationDate, LocalDateTime lastUpdate,
            UserEntity user) {

        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.user = user;
    }

    public NoteEntity(Long id, String title, String text, LocalDateTime creationDate, LocalDateTime lastUpdate,
            UserEntity user) {

        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static NoteEntity FromDTO(NoteDTO noteDTO, UserEntity userEntity) {

        return new NoteEntity(noteDTO.getTitle(), noteDTO.getText(), noteDTO.getCreationDate(),
                noteDTO.getLastUpdate(), userEntity);
    }

    public static NoteEntity BasedOnDomainNote(Note note, UserEntity userEntity) {

        return new NoteEntity(note.getTitle(), note.getText(), note.getCreationDate(), note.getLastUpdate(),
                userEntity);
    }

    public static NoteEntity UpdateNoteEntity(Note note, NoteEntity noteEntity) {

        noteEntity.setTitle(note.getTitle());
        noteEntity.setText(note.getText());
        noteEntity.setLastUpdate(note.getLastUpdate());

        return noteEntity;
    }

    @Override
    public String toString() {
        return "NoteEntity -> \n{ id = " + id +
                ",\n title = " + title +
                ",\n text = " + text +
                ",\n creationDate = " + creationDate +
                ",\n lastUpdate = " + lastUpdate +
                ",\n user = " + user + " }";
    }

}
