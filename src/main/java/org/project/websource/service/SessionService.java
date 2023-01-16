package org.project.websource.service;

import org.project.db.dao.FilmDao;
import org.project.db.dao.SessionDao;
import org.project.db.dao.TicketDao;
import org.project.db.dto.SessionDTO;
import org.project.db.dto.TicketDTO;
import org.project.db.entity.Session;
import org.project.db.entity.Ticket;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SessionService {

    public boolean deleteSession(int id){
        TicketDao.deleteTicketsBySession(id);
        return SessionDao.deleteSession(id);
    }
    public List<SessionDTO> getAllSessions(){
        List<Session> sessions = SessionDao.getAllSessions();
        return parsingSessionInSessionDTO(sessions);

    }
    public List<SessionDTO> getSessionsByFilm(int id){
        List<Session> sessions = SessionDao.getSessionsByFilm(id);
        return parsingSessionInSessionDTO(sessions);
    }

    public SessionDTO  getSessionById(int id){
        Session session = SessionDao.getSessionByID(id);
        List<Session> s = new ArrayList<>();
        s.add(session);
        return parsingSessionInSessionDTO(s).get(0);
    }
    public List<SessionDTO> getAllSessionsSortName(){
        List<Session> sessions = SessionDao.getAllSessionsSortName();
        return parsingSessionInSessionDTO(sessions);
    }
    private List<SessionDTO> parsingSessionInSessionDTO(List<Session> list) {
        List<SessionDTO> sessionDTOS = new ArrayList<>();
        for (Session session : list) {
            sessionDTOS.add(new SessionDTO(session.getId(),
                    session.getTimestamp(),
                    session.getPrice(),
                    session.getFilm().getName(),
                    session.getFilm().getAuthor(),
                    session.getFilm().getCountry(),
                    session.getFilm().getYear(),
                    session.getFilm().getPhoto(),
                    session.getFilm().getDescription(),
                    session.getFilm().getDuration()));
        }
        return sessionDTOS;
    }

    public List<SessionDTO> getAllSessionsCount() {
        List<Session> sessions = SessionDao.getAllSessionsSortCount();
        return parsingSessionInSessionDTO(sessions);
    }

}
