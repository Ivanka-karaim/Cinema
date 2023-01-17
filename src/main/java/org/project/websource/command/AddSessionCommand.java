package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.project.db.dao.FilmDao;
import org.project.db.dao.SessionDao;
import org.project.db.dao.TicketDao;
import org.project.db.dto.FilmDTO;
import org.project.db.dto.SessionDTO;
import org.project.db.entity.Film;
import org.project.db.entity.Session;
import org.project.db.entity.Ticket;
import org.project.websource.Path;
import org.project.websource.service.FilmService;
import org.project.websource.service.SessionService;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddSessionCommand extends Command{
    private static final Logger log = Logger.getLogger(SessionsCommand.class);
    private static final SessionService sessionService = new SessionService();
    private static final FilmService filmService = new FilmService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");
        try {
            sessionService.createSession(request.getParameter("timestamp"), request.getParameter("price"), request.getParameter("film"));
        } catch (Exception e) {
            String error=null;
            if (e.getMessage().equals("error")){
                error="errorRozklad";
//                System.out.println("error");
            }else if (e.getMessage().equals("error1")){
                error = "errorTime";
                System.out.println("error1");
            }else if (e.getMessage().equals("error2")){
                error = "errorTime";
                System.out.println("error2");
            }
            List<FilmDTO> films = filmService.getAllFilms();

            request.setAttribute("films", films);
            request.setAttribute("error", error);
            return Path.PAGE__ADD_SESSION;
//            e.printStackTrace();
        }
        List<SessionDTO> sessions = sessionService.getAllSessions();
        request.setAttribute("session", sessions);
//        try {
//            Session session = new Session();
//            String dateTime = request.getParameter("timestamp");
//            System.out.println(dateTime);
//            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US);
//
//            Date date = inputFormat.parse(dateTime);
//            System.out.println(date);
//            session.setTimestamp(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
//            session.setPrice(Double.parseDouble(request.getParameter("price")));
//            session.setFilm(FilmDao.getFilmById(Integer.parseInt(request.getParameter("film"))));
//
//
//            if (Timestamp.valueOf(session.getTimestamp().toString().split(" ")[0]+" 09:00:00.0").compareTo(session.getTimestamp()) > 0 || Timestamp.valueOf(session.getTimestamp().toString().split(" ")[0]+" 22:00:00.0").compareTo(session.getTimestamp()) < 0){
//                System.out.println("error");
//            }
//            List<Session> session_today = SessionDao.getAllSessionsWhereDate(Timestamp.valueOf(session.getTimestamp().toString().split(" ")[0]+" 00:00:00.0"),Timestamp.valueOf(session.getTimestamp().toString().split(" ")[0]+" 23:59:00.0") );
//            System.out.println(session.getTimestamp().getTime());
//            System.out.println(session.getTimestamp().getTime()+session.getFilm().getDuration().getTime());
//
//            for (Session s: session_today){
//                if (session.getTimestamp().getTime() < s.getTimestamp().getTime()+s.getFilm().getDuration().getTime() && session.getTimestamp().getTime() > s.getTimestamp().getTime()){
//                    System.out.println("error1");
//                }
//                if (session.getTimestamp().getTime()<s.getTimestamp().getTime() && session.getTimestamp().getTime()+session.getFilm().getDuration().getTime() > s.getTimestamp().getTime()){
//                    System.out.println("error2");
//                }
//
//            }
//
//            Session session1 = SessionDao.insertSession(session);
//            for (int i=1; i<=84; i++) {
//                TicketDao.insertTicket(new Ticket(i, session1));
//            }
//
//            List<Session> sessions = SessionDao.getAllSessions();
//            request.setAttribute("session", sessions);
//
//
//            log.debug("Command finished");
//
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
        return Path.PAGE__LIST_MENU_ADMIN;

    }
}
