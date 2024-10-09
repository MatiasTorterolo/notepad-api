package com.mta.notepad_api.notepad_api.dtos;

public class UserDTO {

    private Long id;

    private String username;

    private Long numberNotes;

    public UserDTO(Long id, String username, Long numberNotes) {
        this.id = id;
        this.username = username;
        this.numberNotes = numberNotes;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getNumberNotes() {
        return numberNotes;
    }

    public void setNumberNotes(Long numberNotes) {
        this.numberNotes = numberNotes;
    }

}
