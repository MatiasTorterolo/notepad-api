package com.mta.notepad_api.notepad_api.dtos;

public class UserDTO {

    private String username;

    private String email;

    private String password;

    private boolean asset;

    public UserDTO(String username, String email, String password, boolean asset) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.asset = asset;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAsset() {
        return asset;
    }

    public void setAsset(boolean asset) {
        this.asset = asset;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
