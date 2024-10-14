package com.mta.notepad_api.notepad_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mta.notepad_api.notepad_api.entities.NoteEntity;

public interface INoteRepository extends JpaRepository<NoteEntity, Long> {

}
