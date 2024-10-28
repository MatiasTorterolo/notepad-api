package com.mta.notepad_api.notepad_api.dtos;

import java.time.LocalDateTime;

import com.mta.notepad_api.notepad_api.domain.Note;

public class NoteDTO {

    private String title;

    private String text;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdate;

    public NoteDTO(String title, String text, LocalDateTime creationDate, LocalDateTime lastUpdate) {
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Note asDomainObject() {
        return Note.WrittenWith(this.getTitle(), this.getText());
    }
}
