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
import org.project.service.TicketService;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

public class SessionsCommand extends Command{
    private static final Logger log = Logger.getLogger(SessionsCommand.class);
    private static final SessionService sessionService = new SessionService();
    private static final FilmService filmService = new FilmService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");
        List<SessionDTO> sessions;

        int currentPage;
        if (request.getParameter("currentPage")!=null){
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        else currentPage=1;
        log.trace("Current Page --->"+currentPage);
        if (Objects.equals(request.getParameter("sort"), "name")){
            sessions = sessionService.getAllSessionsSortName(currentPage);
        }else if (Objects.equals(request.getParameter("sort"), "countPlace")){
            sessions = sessionService.getAllSessionsCount(currentPage);
        }
        else{
            sessions = sessionService.getAllSessionsForPagination(currentPage);
        }
        for (SessionDTO s: sessions){
            SimpleDateFormat formatter = new SimpleDateFormat ("dd MMM, HH:mm");
            String dateString = formatter.format(s.getTimestamp());
            s.timestamp_string = dateString;
        }
        int countAll = sessionService.getAllSessions().size();
        int nOfPage = countAll/12 + 1;

        List<Integer> nOfPages = new ArrayList<>();
        for (int i=1; i<=nOfPage; i++){
            nOfPages.add(i);
        }

        request.setAttribute("nOfPages", nOfPages);
        request.setAttribute("session", sessions);
        List<FilmDTO> films = filmService.getAllFilms();
        request.setAttribute("films", films);

        log.debug("Command finished");
        return Path.PAGE__LIST_MENU;
    }
}
