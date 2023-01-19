package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import org.project.dto.FilmDTO;
import org.project.dto.SessionDTO;

import org.project.websource.Path;
import org.project.service.FilmService;
import org.project.service.SessionService;

import java.io.IOException;

import java.util.List;


public class AddSessionCommand extends Command{
    private static final Logger log = Logger.getLogger(AddSessionCommand.class);
    private static final SessionService sessionService = new SessionService();
    private static final FilmService filmService = new FilmService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");
        try {
            sessionService.createSession(request.getParameter("timestamp"), request.getParameter("price"), request.getParameter("film"));
        }catch (IllegalArgumentException e){
            String error = "errorPrice";
            List<FilmDTO> films = filmService.getAllFilms();
            request.setAttribute("films", films);
            log.error("errorMessage --> " + error);
            request.getSession().setAttribute("error", error);
            log.trace("Set the session attribute: error--> " + error);
            return Path.PAGE__EDIT_SESSIONS;
        }catch (Exception e) {
            String error=null;
            if (e.getMessage().equals("error")){
                error="errorRozklad";
            }else if (e.getMessage().equals("error1")){
                error = "errorTime";
            }else if (e.getMessage().equals("error2")){
                error = "errorTime";

            }
//            List<FilmDTO> films = filmService.getAllFilms();
//            request.setAttribute("films", films);
            log.error("errorMessage --> " + error);
            request.getSession().setAttribute("error", error);
            log.trace("Set the session attribute: error--> " + error);
            return Path.PAGE__EDIT_SESSIONS;

        }
        List<SessionDTO> sessions = sessionService.getAllSessions();
        request.setAttribute("session", sessions);
        request.getSession().removeAttribute("error");
        log.debug("Command finished");
        return Path.PAGE__ALL_SESSIONS_ADMIN;

    }
}
