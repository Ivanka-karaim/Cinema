package org.project.db.entity;

import java.sql.Timestamp;
/**

 A class that represents a session of a film screening.

 Extends the {@link Entity} class.
 */
public class Session extends  Entity{
    /**

     The timestamp of the session.
     */
    private Timestamp timestamp;
    /**

     The price of the session.
     */
    private double price;
    /**

     The film of the session.
     */
    private Film film;
    /**

     Returns the timestamp of the session.
     @return the timestamp of the session
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }
    /**

     Returns the price of the session.
     @return the price of the session
     */

    public double getPrice() {
        return price;
    }
    /**

     Returns the film of the session.
     @return the film of the session
     */
    public Film getFilm() {
        return film;
    }
    /**

     Sets the timestamp of the session.
     @param timestamp the timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    /**

     Sets the price of the session.
     @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**

     Sets the film of the session.
     @param film the film to set
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**

     Returns a string representation of the session.
     @return a string representation of the session
     */

    @Override
    public String toString() {
        return "Session{" +
                "timestamp=" + timestamp +
                ", price=" + price +
                ", film=" + film +
                '}';
    }
}
