package org.project.db.entity;
/**

 Represents a ticket for a cinema session.

 Extends the {@link Entity} class.
 */
public class Ticket extends Entity{
    /**

     The number of the seat for this ticket.
     */
    private int place;
    /**

     The user that bought this ticket.
     */
    private User user;
    /**

     The session that this ticket is for.
     */
    private Session session;
    /**

     Constructor with parameters.
     @param place the number of the seat for this ticket.
     @param session the session that this ticket is for.
     */
    public Ticket(int place, Session session){
        this.place = place;
        this.session = session;
    }
    /**

     Default constructor.
     */
    public Ticket() {

    }
    /**

     Getter for the place field.
     @return the number of the seat for this ticket.
     */
    public int getPlace() {
        return place;
    }
    /**

     Getter for the user field.
     @return the user that bought this ticket.
     */

    public User getUser() {
        return user;
    }
    /**

     Getter for the session field.
     @return the session that this ticket is for.
     */
    public Session getSession() {
        return session;
    }
    /**

     Setter for the place field.
     @param place the new number of the seat for this ticket.
     */
    public void setPlace(int place) {
        this.place = place;
    }
    /**

     Setter for the user field.
     @param user the new user that bought this ticket.
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**

     Setter for the session field.
     @param session the new session that this ticket is for.
     */
    public void setSession(Session session) {
        this.session = session;
    }
    /**

     Returns a String representation of this Ticket object.
     @return a String representation of this Ticket object.
     */
    @Override
    public String toString() {
        return "Tickets{" +
                "place=" + place +
                ", user=" + user +
                ", session=" + session +
                '}';
    }
}
