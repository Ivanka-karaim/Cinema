package org.project.db.entity;
/**

 The Genre class represents a film genre.

 It extends the abstract Entity class.
 */
public class Genre extends Entity{
    /**

     The name of the genre.
     */
    private String  name;
    /**

     Gets the name of the genre.
     @return the name of the genre
     */

    public String getName() {
        return name;
    }
    /**

     Sets the name of the genre.
     @param name the name of the genre
     */

    public void setName(String name) {
        this.name = name;
    }
}
