package org.project.db.entity;


import java.sql.Time;
import java.util.List;

public class Film extends Entity{
    private String name;
    private String author;
    private String country;
    private int year;
    private String description;
    private String photo;
    private Time duration;
    private List<Genre> genres;

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public Time getDuration() {
        return duration;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", country='" + country + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", duration=" + duration +
                '}';
    }
}
