package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.db.dao.FilmDao;
import org.project.db.dao.SessionDao;
import org.project.db.dto.SessionDTO;
import org.project.db.entity.Film;
import org.project.db.entity.Session;
import org.project.websource.Path;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class FilterCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        System.out.println(request.getParameter("film"));
        int id = Integer.parseInt(request.getParameter("film"));
        List<Session> sessions = SessionDao.getSessionsByFilm(id);

        request.setAttribute("session", sessions);
        List<Film> films = FilmDao.getAllFilms();
        request.setAttribute("films", films);
        return Path.PAGE__LIST_MENU;
    }
}
