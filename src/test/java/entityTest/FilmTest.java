package entityTest;

import org.junit.Test;
import org.project.db.entity.Film;

import java.sql.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FilmTest {
    @Test
    public void testFilmEntity(){
        Film film = new Film();
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
