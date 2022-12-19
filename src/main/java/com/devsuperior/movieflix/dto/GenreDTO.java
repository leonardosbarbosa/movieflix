package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Genre;

import java.io.Serializable;

public class GenreDTO implements Serializable {
    private static final long serialVersionUID = -1302391131771935100L;

    private Long id;
    private String name;

    public GenreDTO() {
    }

    public GenreDTO(Genre entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
