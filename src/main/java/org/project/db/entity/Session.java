package org.project.db.entity;

import java.sql.Timestamp;

public class Session extends  Entity{
    private Timestamp timestamp;
    private double price;
    private Film film;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public double getPrice() {
        return price;
    }

    public Film getFilm() {
        return film;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFilm(Film film) {
        this.film = film;
    }



    @Override
    public String toString() {
        return "Session{" +
                "timestamp=" + timestamp +
                ", price=" + price +
                ", film=" + film +
                '}';
    }
}
