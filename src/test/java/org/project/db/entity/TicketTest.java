package org.project.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TicketTest {
    Ticket ticket;
    User user;
    Session session;
    Film film;

    @BeforeEach
    void setUp() {
        ticket = new Ticket();
        user = new User();
        session = new Session();
        film = new Film();

    }

    @Test
    void testTicketEntity(){
        user.setName("Ivanna");
        user.setId(1);
        user.setSurname("Karaim");

        film.setName("One at home");

        session.setFilm(film);
        session.setFilm(film);
        session.setTimestamp(Timestamp.valueOf("2023-09-03 20:00:00"));

        ticket.setUser(user);
        ticket.setPlace(25);
        ticket.setSession(session);


        assertEquals(25, ticket.getPlace());
        assertEquals("2023-09-03 20:00:00.0", ticket.getSession().getTimestamp().toString());
        assertEquals("Ivanna", ticket.getUser().getName());

    }
}