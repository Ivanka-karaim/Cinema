package org.project.db.entity;

public class Ticket extends Entity{

    private int place;
    private User user;
    private Session session;
    public Ticket(int place, Session session){
        this.place = place;
        this.session = session;
    }

    public Ticket() {

    }

    public int getPlace() {
        return place;
    }

    public User getUser() {
        return user;
    }

    public Session getSession() {
        return session;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "place=" + place +
                ", user=" + user +
                ", session=" + session +
                '}';
    }
}