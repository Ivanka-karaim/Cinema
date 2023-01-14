package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.project.db.dao.SessionDao;
import org.project.db.entity.Session;
import org.project.websource.Path;

import java.io.IOException;
import java.util.List;

public class SessionsCommand extends Command{
    private static final Logger log = Logger.getLogger(SessionsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");
        List<Session> sessions = SessionDao.getAllSessions();
        request.setAttribute("session", sessions);
        System.out.println(sessions.size());

        log.debug("Command finished");
        return Path.PAGE__LIST_MENU;
    }
}
