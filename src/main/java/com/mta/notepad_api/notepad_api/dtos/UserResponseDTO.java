package com.mta.notepad_api.notepad_api.dtos;

public class UserResponseDTO {

    private String username;

    public UserResponseDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
