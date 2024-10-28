package com.mta.notepad_api.notepad_api.dtos;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.mta.notepad_api.notepad_api.entities.NoteEntity;

public class NoteResponseDTO {

    private Long id;

    private String title;

    private String text;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdate;

    public NoteResponseDTO() {
    }

    public NoteResponseDTO(Long id, String title, String text, LocalDateTime creationDate, LocalDateTime lastUpdate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public static List<NoteResponseDTO> ToListNoteResponseDTO(List<NoteEntity> listEntity) {

        if (listEntity == null) {

            return Collections.emptyList();
        }

        return listEntity.stream().map(noteEntity -> {

            NoteResponseDTO noteResponseDTO = new NoteResponseDTO();
            noteResponseDTO.setId(noteEntity.getId());
            noteResponseDTO.setTitle(noteEntity.getTitle());
            noteResponseDTO.setText(noteEntity.getTitle());
            noteResponseDTO.setCreationDate(noteEntity.getCreationDate());
            noteResponseDTO.setLastUpdate(noteEntity.getLastUpdate());

            return noteResponseDTO;
        }).collect(Collectors.toList());
    }

    public static NoteResponseDTO ToNoteResponseDTO(NoteEntity noteEntity) {

        NoteResponseDTO noteResponseDTO = new NoteResponseDTO(noteEntity.getId(), noteEntity.getTitle(),
                noteEntity.getText(), noteEntity.getCreationDate(), noteEntity.getLastUpdate());

        return noteResponseDTO;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NoteResponseDTO other = (NoteResponseDTO) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }

}
