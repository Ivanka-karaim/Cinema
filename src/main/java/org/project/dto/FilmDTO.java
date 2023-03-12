package org.project.dto;
/**

 The FilmDTO class represents a film data transfer object, containing an ID and name for a film.
 */
public class FilmDTO {
    /**

     The ID of the film.
     */
    public int id;
    /**

     The name of the film.
     */
    public String name;
    /**

     Constructs a new FilmDTO object with the given ID and name.
     @param id the ID of the film
     @param name the name of the film
     */
    public FilmDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**

     Returns the ID of the film.
     @return the ID of the film
     */

    public int getId() {
        return id;
    }
    /**

     Returns the name of the film.
     @return the name of the film
     */

    public String getName() {
        return name;
    }
}
