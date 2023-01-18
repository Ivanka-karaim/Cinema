package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.project.dto.SessionDTO;
import org.project.dto.TicketDTO;
import org.project.websource.Path;
import org.project.service.SessionService;
import org.project.service.TicketService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class SessionCommand extends Command{
    private static final Logger log = Logger.getLogger(SessionsCommand.class);
    private static final SessionService sessionService = new SessionService();
    private static final TicketService ticketService = new TicketService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
//        Session session = SessionDao.getSessionByID(id);
        SessionDTO session  = sessionService.getSessionById(id);
        System.out.println(session);
        request.setAttribute("session", session);


//        List<Ticket> tickets = TicketDao.get_tickets(session);
        List<TicketDTO> tickets = ticketService.getTickets(id);
        System.out.println(tickets);
        request.setAttribute("tickets", tickets);
        return Path.PAGE__SESSION;
    }
}
