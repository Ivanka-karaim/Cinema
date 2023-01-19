package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.project.dto.FilmDTO;
import org.project.websource.Path;
import org.project.service.FilmService;

import java.io.IOException;
import java.util.List;

public class AddSessionFormCommand extends Command{
    private static final Logger log = Logger.getLogger(AddSessionFormCommand.class);
    private static final FilmService filmService = new FilmService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");
        List<FilmDTO> films = filmService.getAllFilms();
        request.setAttribute("films", films);
        log.debug("Command finished");
        return Path.PAGE__ADD_SESSION;
    }
}
