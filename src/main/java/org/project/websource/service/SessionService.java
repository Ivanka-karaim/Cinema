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
    private static final int COUNT_PLACE_HALL=84;

    public boolean deleteSession(int id){
        TicketDao.deleteTicketsBySession(id);
        return SessionDao.deleteSession(id);
    }
    public List<SessionDTO> getAllSessions(){
        List<Session> sessions = SessionDao.getAllSessions();
        return parsingSessionInSessionDTO(sessions);

    }
    public List<SessionDTO> getAllSessionsForPagination(int currentPage){
        int start = currentPage * 12 - 12;
        List<Session> sessions1 = SessionDao.getAllSessionsForPagination(start, 12);
        return parsingSessionInSessionDTO(sessions1);
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
    public List<SessionDTO> getAllSessionsSortName(int currentPage){
        int start = currentPage * 12 - 12;
        List<Session> sessions = SessionDao.getAllSessionsSortName(start, 12);
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

    public List<SessionDTO> getAllSessionsCount(int currentPage) {
        int start = currentPage * 12 - 12;
        List<Session> sessions = SessionDao.getAllSessionsSortCount(start, 12);
        return parsingSessionInSessionDTO(sessions);
    }
    public boolean createSession(String timestamp, String price, String film) throws Exception {
        Session session = new Session();
        String dateTime = timestamp;
        System.out.println(dateTime);
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US);

        Date date = null;
        try {
            date = inputFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(date);
        session.setTimestamp(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
        session.setPrice(Double.parseDouble(price));
        session.setFilm(FilmDao.getFilmById(Integer.parseInt(film)));

        if (Timestamp.valueOf(session.getTimestamp().toString().split(" ")[0]+" 09:00:00.0").compareTo(session.getTimestamp()) > 0 || Timestamp.valueOf(session.getTimestamp().toString().split(" ")[0]+" 22:00:00.0").compareTo(session.getTimestamp()) < 0){
            throw new Exception("error");

//            System.out.println("error");
        }
        List<Session> session_today = SessionDao.getAllSessionsWhereDate(Timestamp.valueOf(session.getTimestamp().toString().split(" ")[0]+" 00:00:00.0"),Timestamp.valueOf(session.getTimestamp().toString().split(" ")[0]+" 23:59:00.0") );
//        System.out.println(session.getTimestamp().getTime());
//        System.out.println(session.getTimestamp().getTime()+session.getFilm().getDuration().getTime());

        for (Session s: session_today){
            if (session.getTimestamp().getTime() < s.getTimestamp().getTime()+s.getFilm().getDuration().getTime() && session.getTimestamp().getTime() > s.getTimestamp().getTime()){
                throw new Exception("error1");
//                System.out.println("error1");
            }
            if (session.getTimestamp().getTime()<s.getTimestamp().getTime() && session.getTimestamp().getTime()+session.getFilm().getDuration().getTime() > s.getTimestamp().getTime()){
                throw new Exception("error2");
//                System.out.println("error2");
            }

        }

        Session session1 = SessionDao.insertSession(session);
        for (int i=1; i<=COUNT_PLACE_HALL; i++) {
            TicketDao.insertTicket(new Ticket(i, session1));
        }
        return true;
    }

}
