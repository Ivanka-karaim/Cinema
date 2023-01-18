package entityTest;

import org.junit.Test;
import org.project.db.dao.FilmDao;
import org.project.db.entity.Film;
import org.project.db.entity.Session;

import java.sql.Time;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SessionTest {

    @Test
    public void testSessionEntity(){
        Session session = new Session();
        assertNotNull(session);
        Film film = FilmDao.getFilmById(1);
        session.setFilm(film);
        session.setPrice(240);
        session.setTimestamp(Timestamp.valueOf("2023-02-03 20:00:00"));

        assert film != null;
        assertEquals(session.getFilm().getId(), film.getId());

        assertEquals(240.0, session.getPrice(), 0);

        assertEquals(session.getTimestamp().toString(), "2023-02-03 20:00:00.0");


    }

}
