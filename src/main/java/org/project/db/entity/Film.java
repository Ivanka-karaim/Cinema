package org.project.db.entity;


import java.sql.Time;
import java.util.List;
/**

 Represents a film in the system.

 <p>This class extends the {@code Entity} class, which provides a unique
 identifier for each film.</p>
 */
public class Film extends Entity{

    private String name;

    private String author;
    private String country;
    private int year;
    private String description;
    private String photo;
    private Time duration;
    private List<Genre> genres;
    /**

     Gets the name of the film.
     @return the name of the film
     */
    public String getName() {
        return name;
    }
    /**

     Gets the author of the film.
     @return the author of the film
     */
    public String getAuthor() {
        return author;
    }
    /**

     Gets the country of the film.
     @return the country of the film
     */
    public String getCountry() {
        return country;
    }
    /**

     Gets the year the film was released.
     @return the year the film was released
     */
    public int getYear() {
        return year;
    }
    /**

     Gets the description of the film.
     @return the description of the film
     */
    public String getDescription() {
        return description;
    }
    /**

     Gets the photo of the film.
     @return the photo of the film
     */
    public String getPhoto() {
        return photo;
    }
    /**

     Gets the duration of the film.
     @return the duration of the film
     */
    public Time getDuration() {
        return duration;
    }
    /**

     Gets the genres of the film.
     @return the genres of the film
     */
    public List<Genre> getGenres() {
        return genres;
    }
    /**

     Sets the name of the film.
     @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**

     Sets the author of the film.
     @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**

     Sets the country of the film.
     @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**

     Sets the year the film was released.
     @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }
    /**

     Sets the description of the film.
     @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**

     Sets the photo of the film.
     @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    /**

     Sets the duration of the film.
     @param duration the duration to set
     */
    public void setDuration(Time duration) {
        this.duration = duration;
    }
    /**

     Sets the genres of the film.
     @param genres the genres to set
     */
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
    /**

     Returns a string representation of the film.
     @return a string representation of the film
     */
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
