package org.project.db.entity;


//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Time;

import static org.junit.Assert.*;

public class FilmTest {
    Film film;

    @BeforeEach
    public void setUp() throws Exception {
        film = new Film();
    }


    @Test
    void testFilmEntity() {

        assertNotNull(film);
        film.setName("Один вдома");
        film.setAuthor("Кріс Коламбус");
        film.setYear(1998);
        film.setDuration(new Time(1,30,0));


        assertEquals("Один вдома", film.getName());
        assertEquals("Кріс Коламбус", film.getAuthor());
        assertEquals(1998, film.getYear());
        assertEquals("01:30:00", film.getDuration().toString());
    }



}