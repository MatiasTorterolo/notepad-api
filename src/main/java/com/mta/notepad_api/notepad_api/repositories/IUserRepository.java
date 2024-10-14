package com.mta.notepad_api.notepad_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mta.notepad_api.notepad_api.entities.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
