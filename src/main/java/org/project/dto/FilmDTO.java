package org.project.dto;

public class FilmDTO {
    public int id;
    public String name;

    public FilmDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
