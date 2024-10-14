package com.mta.notepad_api.notepad_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mta.notepad_api.notepad_api.entities.NoteEntity;

public interface INoteRepository extends JpaRepository<NoteEntity, Long> {

    @Query("SELECT n FROM NoteEntity n where n.user.id = :userId")
    List<NoteEntity> findAllNotes(Long userId);
}
