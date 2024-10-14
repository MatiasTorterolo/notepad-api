package com.mta.notepad_api.notepad_api.domain;

import java.time.LocalDateTime;

public class Note {

    private String title;

    private String text;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdate;

    public Note(String title, String text, LocalDateTime creationDate, LocalDateTime lastUpdate) {
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

    public int wordCount(String text) {

        if (text == null || text.isEmpty()) {

            return 0;
        }

        String[] words = text.trim().split("\\s+");

        return words.length;
    }

}
