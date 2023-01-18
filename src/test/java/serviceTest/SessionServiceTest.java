package serviceTest;

import org.junit.Test;
import org.project.db.dao.SessionDao;
import org.project.db.entity.Session;
import org.project.dto.SessionDTO;
import org.project.service.SessionService;

import java.util.List;
import static org.junit.Assert.assertEquals;


public class SessionServiceTest {
    @Test
    public void testSessionService(){
        SessionService ss = new SessionService();
        List<SessionDTO> sessions = ss.getAllSessionsForPagination(2);

        assertEquals(2, sessions.size());

        List<Session> sessionList = SessionDao.getAllSessions();

        List<SessionDTO> sessionDTOS = ss.getAllSessions();

        assertEquals(sessionList.size(), sessionDTOS.size());

        assertEquals(sessionList.get(5).getTimestamp(), sessionDTOS.get(5).getTimestamp());

    }
}
