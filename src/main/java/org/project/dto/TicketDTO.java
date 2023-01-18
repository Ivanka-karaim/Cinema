package org.project.dto;

import org.project.db.entity.User;

import java.sql.Timestamp;

public class TicketDTO {
    public int id;
    public int place;
    public String film_name;
    public double price;
    public Timestamp timestamp;
    public boolean user;

    public TicketDTO(){

    }
    public TicketDTO(int id, int place,  String film_name, double price, Timestamp timestamp, User user) {
        this.id = id;
        this.place = place;
        this.film_name = film_name;
        this.price = price;
        this.timestamp = timestamp;
        this.user = user != null;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "place=" + place +
                ", film_name='" + film_name + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }

    public int getPlace() {
        return place;
    }

    public String getFilm_name() {
        return film_name;
    }

    public double getPrice() {
        return price;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getId() {
        return id;
    }

    public boolean isUser() {
        return user;
    }
}
