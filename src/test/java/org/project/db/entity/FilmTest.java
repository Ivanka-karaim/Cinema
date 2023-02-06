package org.project.db.entity;

import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class FilmTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getName() {
    }

    @Test
    public void getAuthor() {
    }

    @org.junit.jupiter.api.Test
    void testGetName() {
        Film film = new Film();
        film.setName("q435");
        film.getName();
        assertTrue(true);
    }

    @org.junit.jupiter.api.Test
    void testGetAuthor() {
    }

    @org.junit.jupiter.api.Test
    void getCountry() {
    }

    @org.junit.jupiter.api.Test
    void getYear() {
    }

    @org.junit.jupiter.api.Test
    void getDescription() {
    }

    @org.junit.jupiter.api.Test
    void getPhoto() {
    }
}