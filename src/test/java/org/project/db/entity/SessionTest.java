package org.project.db.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.project.db.dao.FilmDao;

import java.sql.Timestamp;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {
    Session session;
    Film film;

    @BeforeEach
    void setUp() {
        session = new Session();
        film = mock(Film.class);
    }
    @Test
    void testSessionEntity(){
        assertNotNull(session);
        film = FilmDao.getFilmById(1);
        session.setFilm(film);
        session.setPrice(240);
        session.setTimestamp(Timestamp.valueOf("2023-02-03 20:00:00"));

        assert film != null;
        Assertions.assertEquals(session.getFilm().getId(), film.getId());

        assertEquals(240.0, session.getPrice(), 0);

        assertEquals(session.getTimestamp().toString(), "2023-02-03 20:00:00.0");

    }
}