package daoTest;

import org.junit.Test;
import org.project.db.dao.SessionDao;
import org.project.db.entity.Film;
import org.project.db.entity.Session;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.random;
import static org.junit.Assert.assertEquals;

public class SessionDaoTest {
    @Test
    public void testSessionDaoInsertDelete(){
        List<Session> sessions = SessionDao.getAllSessions();
        Session s1 = new Session();
        Film film = new Film();
        film.setId(2);
        s1.setTimestamp(Timestamp.valueOf("2023-02-03 12:30:00"));
        s1.setPrice(250);
        s1.setFilm(film);

        Session sInsert =  SessionDao.insertSession(s1);

        assertEquals(sessions.size()+1, SessionDao.getAllSessions().size());

        assert sInsert != null;
        SessionDao.deleteSession(sInsert.getId());

        assertEquals(sessions.size(), SessionDao.getAllSessions().size());

    }

    @Test
    public void testSessionDaoSort(){
        List<Session> sessionsSortName = SessionDao.getAllSessionsSortName(0, 100);
        List<Session> sessionsSortName2 =  SessionDao.getAllSessionsForPagination(0, 100);

        sessionsSortName2.sort(new Comparator<Session>() {
            @Override
            public int compare(Session o1, Session o2) {
                return o1.getFilm().getName().compareTo(o2.getFilm().getName());
            }
        });

        assertEquals(sessionsSortName.toString(), sessionsSortName2.toString());

    }

    @Test
    public void testSessionDao(){
        List<Session> sessions = SessionDao.getAllSessions();
        System.out.println(sessions);
        Session s1 = SessionDao.getSessionByID(sessions.get(1).getId());
        assert s1 != null;
        assertEquals(sessions.get(1).toString(), s1.toString());

        List<Session> sessions2 = SessionDao.getAllSessionsWhereDate(Timestamp.valueOf("2023-02-01 00:00:00.0"), Timestamp.valueOf("2023-02-01 23:59:00.0"));
        assertEquals(sessions2.get(1).getTimestamp().toString().split(" ")[0], sessions2.get(2).getTimestamp().toString().split(" ")[0]);

    }


}
