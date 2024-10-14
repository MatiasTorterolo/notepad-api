package com.mta.notepad_api.notepad_api.dtos;

public class UserResponseDTO {

    private Long id;

    private String username;

    public UserResponseDTO(Long id, String username) {
        this.id = id;
        this.username = username;
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

}
