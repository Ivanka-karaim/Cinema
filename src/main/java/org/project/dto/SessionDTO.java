package org.project.dto;

import java.sql.Time;
import java.sql.Timestamp;
/**

 The SessionDTO class represents a session data transfer object, containing various details about a film session.
 */
public class SessionDTO {
    /**

     The ID of the session.
     */
    public int id;
    /**

     The timestamp of the session.
     */
    public Timestamp timestamp;
    /**

     The price of the session.
     */
    public double price;
    /**

     The timestamp of the session as a string.
     */
    public String timestamp_string=null;
    /**

     The name of the film being shown in the session.
     */
    public String name;
    /**

     The author of the film being shown in the session.
     */
    public String author;
    /**

     The country of origin of the film being shown in the session.
     */
    public String country;
    /**

     The year of release of the film being shown in the session.
     */

    public int year;
    /**

     A description of the film being shown in the session.
     */
    public String description;
    /**

     A photo of the film being shown in the session.
     */
    public String photo;
    /**

     The duration of the film being shown in the session.
     */
    public Time duration;
    /**

     Constructs a new SessionDTO object with the given details.
     @param id the ID of the session
     @param timestamp the timestamp of the session
     @param price the price of the session
     @param name the name of the film being shown in the session
     @param author the author of the film being shown in the session
     @param country the country of origin of the film being shown in the session
     @param year the year of release of the film being shown in the session
     @param description a description of the film being shown in the session
     @param photo a photo of the film being shown in the session
     @param duration the duration of the film being shown in the session
     */

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
    /**

     Returns the ID of the session.
     @return the ID of the session
     */
    public int getId() {
        return id;
    }
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

     Returns the timestamp of the session as a string.
     @return the timestamp of the session as a string
     */
    public String getTimestamp_string() {
        return timestamp_string;
    }
    /**

     Returns the name of the film being shown in the session.
     @return the name of the film being shown in the session
     */
    public String getName() {
        return name;
    }
    /**

     Returns the author of the movie.
     @return The author of the movie.
     */
    public String getAuthor() {
        return author;
    }
    /**

     Returns the country of the movie.
     @return The country of the movie.
     */
    public String getCountry() {
        return country;
    }
    /**

     Returns the year of the movie.
     @return The year of the movie.
     */
    public int getYear() {
        return year;
    }
    /**

     Returns the description of the movie.
     @return The secription of the movie.
     */
    public String getDescription() {
        return description;
    }
    /**

     Returns the photo of the movie.
     @return The photo of the movie.
     */
    public String getPhoto() {
        return photo;
    }
    /**

     Returns the duration of the movie.
     @return The duration of the movie.
     */
    public Time getDuration() {
        return duration;
    }
}
