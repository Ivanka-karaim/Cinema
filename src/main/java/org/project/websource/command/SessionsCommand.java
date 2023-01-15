package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.project.db.dao.FilmDao;
import org.project.db.dao.SessionDao;
import org.project.db.dao.TicketDao;
import org.project.db.entity.Film;
import org.project.db.entity.Session;
import org.project.db.entity.Ticket;
import org.project.websource.Path;
import org.project.websource.service.TicketService;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SessionsCommand extends Command{
    private static final Logger log = Logger.getLogger(SessionsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");
        List<Session> sessions;
        if (Objects.equals(request.getParameter("sort"), "name")){
            sessions = SessionDao.getAllSessionsSortName();

        }else if (Objects.equals(request.getParameter("sort"), "countPlace")){
            sessions = SessionDao.getAllSessionsCount();
        }
        else{
            sessions = SessionDao.getAllSessions();
        }
        for (Session s: sessions){
            SimpleDateFormat formatter = new SimpleDateFormat ("dd MMM, HH:mm");
            String dateString = formatter.format(s.getTimestamp());
            System.out.println(dateString);
//            s.setTimestamp(Timestamp.valueOf(dateString));
        }
        request.setAttribute("session", sessions);
        List<Film> films = FilmDao.getAllFilms();
        request.setAttribute("films", films);
        System.out.println(sessions.size());

        log.debug("Command finished");
        return Path.PAGE__LIST_MENU;
    }
}
