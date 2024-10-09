package com.mta.notepad_api.notepad_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private Long numberNotes;

    public UserEntity(String username, String email, String password, Long numberNotes) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.numberNotes = numberNotes;
    }

    public Long getId() {
        return id;
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
