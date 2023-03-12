package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.project.db.dao.SessionDao;
import org.project.db.entity.Film;
import org.project.db.entity.Session;
import org.project.websource.Path;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteSessionCommandTest {
    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @InjectMocks
    DeleteSessionCommand deleteSessionCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void execute() throws ServletException, IOException {
        Film film = new Film();
        film.setId(1);
        Session session = new Session();
        session.setFilm(film);
        session.setTimestamp(Timestamp.valueOf("2023-12-07 12:00:00"));
        session.setPrice(200);

        SessionDao.insertSession(session);
        List<Session> sessions = SessionDao.getAllSessions();

        Mockito.when(req.getParameter("id")).thenReturn(String.valueOf(sessions.get(sessions.size()-1).getId()));
        String result = deleteSessionCommand.execute(req, resp);
        assertEquals(Path.PAGE__LIST_MENU_ADMIN, result);
    }
}