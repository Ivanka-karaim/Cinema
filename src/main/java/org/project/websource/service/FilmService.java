package org.project.websource.service;

import org.project.db.dao.FilmDao;
import org.project.db.dto.FilmDTO;
import org.project.db.dto.TicketDTO;
import org.project.db.entity.Film;
import org.project.db.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class FilmService {
    public List<FilmDTO> getAllFilms() {
        List<Film> films = FilmDao.getAllFilms();
        return parsingFilmInFilmDTO(films);
    }

    private List<FilmDTO> parsingFilmInFilmDTO(List<Film> list) {
        List<FilmDTO> filmDTOs = new ArrayList<>();
        for (Film film : list) {
            filmDTOs.add(new FilmDTO(film.getId(), film.getName()));
        }
        return filmDTOs;
    }
}
