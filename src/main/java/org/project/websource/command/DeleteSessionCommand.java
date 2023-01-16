package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.project.db.dao.SessionDao;
import org.project.db.dao.TicketDao;
import org.project.db.dto.SessionDTO;
import org.project.db.entity.Session;
import org.project.websource.Path;
import org.project.websource.service.SessionService;
import org.project.websource.service.TicketService;

import java.io.IOException;
import java.util.List;

public class DeleteSessionCommand extends Command{
    private static final Logger log = Logger.getLogger(SessionsCommand.class);
    private static final SessionService sessionService = new SessionService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");
        int id = Integer.parseInt(request.getParameter("id"));
        sessionService.deleteSession(id);

//        List<Session> sessions = SessionDao.getAllSessions();
        List<SessionDTO> sessions = sessionService.getAllSessions();
        request.setAttribute("session", sessions);
        System.out.println(sessions.size());


        log.debug("Command finished");
        return Path.PAGE__LIST_MENU_ADMIN;
    }
}
