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
import java.text.ParseException;
import java.util.List;

public class FilterCommand extends Command{
    private static final Logger log = Logger.getLogger(FilterCommand.class);
    private static final SessionService sessionService = new SessionService();
    private static final FilmService filmService = new FilmService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        log.debug("Command starts");
        int id = Integer.parseInt(request.getParameter("film"));
        List<SessionDTO> sessions = sessionService.getSessionsByFilm(id);
        request.setAttribute("session", sessions);
        List<FilmDTO> films = filmService.getAllFilms();
        request.setAttribute("films", films);
        log.debug("Command finished");
        return Path.PAGE__LIST_MENU;
    }
}
