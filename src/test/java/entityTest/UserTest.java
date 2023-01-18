package entityTest;

import org.junit.Test;
import org.project.db.dao.FilmDao;
import org.project.db.entity.Film;
import org.project.db.entity.Session;
import org.project.db.entity.User;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Test
    public void testUserEntity(){
        User user = new User();
        assertNotNull(user);
        user.setName("Ivan");
        user.setSurname("Karaim");
        user.setRole("user");
        user.setPhone_number("+380667236485");
        user.setDate_birth(new Date(2003, Calendar.JULY, 8));

        assertEquals("Ivan", user.getName());
        assertEquals("Karaim", user.getSurname());
        assertEquals("user", user.getRole());
        assertEquals("+380667236485", user.getPhone_number());
        assertEquals("Wed Jul 08 00:00:00 EEST 3903", user.getDate_birth().toString());
    }


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


    }}
