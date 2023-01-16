package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.db.dao.FilmDao;
import org.project.db.dto.FilmDTO;
import org.project.db.dto.SessionDTO;
import org.project.db.entity.Film;
import org.project.websource.Path;
import org.project.websource.service.FilmService;
import org.project.websource.service.SessionService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class FilterCommand extends Command{
    private static final SessionService sessionService = new SessionService();
    private static final FilmService filmService = new FilmService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        System.out.println(request.getParameter("film"));
        int id = Integer.parseInt(request.getParameter("film"));
        List<SessionDTO> sessions = sessionService.getSessionsByFilm(id);

        request.setAttribute("session", sessions);

//        List<Film> films = FilmDao.getAllFilms();
        List<FilmDTO> films = filmService.getAllFilms();
        request.setAttribute("films", films);
        return Path.PAGE__LIST_MENU;
    }
}
