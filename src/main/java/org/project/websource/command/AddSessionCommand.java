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
    private static final Logger log = Logger.getLogger(SessionsCommand.class);
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
            request.setAttribute("error", error);
            return Path.PAGE__ADD_SESSION;
        }catch (Exception e) {
            String error=null;
            if (e.getMessage().equals("error")){
                error="errorRozklad";
//                System.out.println("error");
            }else if (e.getMessage().equals("error1")){
                error = "errorTime";
//                System.out.println("error1");
            }else if (e.getMessage().equals("error2")){
                error = "errorTime";
//                System.out.println("error2");
            }
            List<FilmDTO> films = filmService.getAllFilms();
            request.setAttribute("films", films);
            request.setAttribute("error", error);
            return Path.PAGE__ADD_SESSION;

        }
        List<SessionDTO> sessions = sessionService.getAllSessions();
        request.setAttribute("session", sessions);
        return Path.PAGE__LIST_MENU_ADMIN;

    }
}
