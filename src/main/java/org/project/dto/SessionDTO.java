package org.project.dto;

import java.sql.Time;
import java.sql.Timestamp;

public class SessionDTO {
    public int id;
    public Timestamp timestamp;
    public double price;
    public String timestamp_string=null;
    public String name;
    public String author;
    public String country;
    public int year;
    public String description;
    public String photo;
    public Time duration;

    public SessionDTO(int id, Timestamp timestamp, double price,  String name, String author, String country, int year, String description, String photo, Time duration) {
        this.id = id;
        this.timestamp = timestamp;
        this.price = price;
        this.name = name;
        this.author = author;
        this.country = country;
        this.year = year;
        this.description = description;
        this.photo = photo;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public double getPrice() {
        return price;
    }

    public String getTimestamp_string() {
        return timestamp_string;
    }

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
}
