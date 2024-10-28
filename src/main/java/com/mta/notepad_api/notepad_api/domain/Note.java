package com.mta.notepad_api.notepad_api.domain;

import java.time.LocalDateTime;

public class Note {

    private String title;

    private String text;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdate;

    private Note(String title, String text, LocalDateTime creationDate, LocalDateTime lastUpdate) {

        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }

    public static Note WrittenWith(String title, String text) {

        Note.AssertTitleAndTextIsDefined(title, text);

        LocalDateTime now = LocalDateTime.now();

        return new Note(title, text, now, now);
    }

    public static Note WrittenAt(String title, String text, LocalDateTime creationDate) {

        return new Note(title, text, creationDate, LocalDateTime.now());
    }

    private static void AssertTitleAndTextIsDefined(String title, String text) {

        if ((title == null || title.isEmpty()) && (text == null || text.isEmpty())) {
            throw new IllegalArgumentException("Title and text must be defined");
        }
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

}
