package com.mta.notepad_api.notepad_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mta.notepad_api.notepad_api.entities.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findByUsername(String username);
}
