package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.project.db.dao.SessionDao;
import org.project.db.dao.TicketDao;
import org.project.db.entity.Session;
import org.project.db.entity.Ticket;
import org.project.websource.Path;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class SessionCommand extends Command{
    private static final Logger log = Logger.getLogger(SessionsCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        Session session = SessionDao.getSessionByID(id);
        System.out.println(session);
        request.setAttribute("session", session);

        List<Ticket> tickets = TicketDao.get_tickets(session);
        System.out.println(tickets);
        request.setAttribute("tickets", tickets);
        return Path.PAGE__SESSION;
    }
}
