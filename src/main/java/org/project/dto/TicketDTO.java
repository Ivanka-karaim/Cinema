package org.project.dto;

import org.project.db.entity.User;

import java.sql.Timestamp;
/**

 The TicketDTO class represents a data transfer object for a movie ticket.

 It contains information such as the ticket id, place number, film name, price, and timestamp.

 Additionally, it includes a flag indicating if the ticket has been purchased by a user.
 */
public class TicketDTO {
    /**

     The id of the ticket.
     */
    public int id;
    /**

     The number of the seat/place associated with the ticket.
     */
    public int place;
    /**

     The name of the film associated with the ticket.
     */
    public String film_name;
    /**

     The price of the ticket.
     */
    public double price;
    /**

     The timestamp of the ticket.
     */
    public Timestamp timestamp;
    /**

     Flag indicating whether the ticket has been purchased by a user.
     */
    public boolean user;
    /**

     Default constructor for the TicketDTO class.
     */
    public TicketDTO(){

    }
    /**

     Constructor for the TicketDTO class.
     @param id the id of the ticket
     @param place the number of the seat/place associated with the ticket
     @param film_name the name of the film associated with the ticket
     @param price the price of the ticket
     @param timestamp the timestamp of the ticket
     @param user the user that purchased the ticket
     */
    public TicketDTO(int id, int place,  String film_name, double price, Timestamp timestamp, User user) {
        this.id = id;
        this.place = place;
        this.film_name = film_name;
        this.price = price;
        this.timestamp = timestamp;
        this.user = user != null;
    }
    /**

     Returns a string representation of the TicketDTO object.
     @return a string representation of the TicketDTO object
     */
    @Override
    public String toString() {
        return "TicketDTO{" +
                "place=" + place +
                ", film_name='" + film_name + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
    /**

     Returns the number of the seat/place associated with the ticket.
     @return the number of the seat/place associated with the ticket
     */
    public int getPlace() {
        return place;
    }
    /**

     Returns the name of the film associated with the ticket.
     @return the name of the film associated with the ticket
     */
    public String getFilm_name() {
        return film_name;
    }
    /**

     Returns the price of the ticket.
     @return the price of the ticket
     */
    public double getPrice() {
        return price;
    }
    /**

     Returns the timestamp of the ticket.
     @return the timestamp of the ticket
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }
    /**

     Returns the id of the ticket.
     @return the id of the ticket
     */
    public int getId() {
        return id;
    }
    /**

     Returns whether the ticket has been purchased by a user.
     @return true if the ticket has been purchased by a user, false otherwise
     */
    public boolean isUser() {
        return user;
    }
}
