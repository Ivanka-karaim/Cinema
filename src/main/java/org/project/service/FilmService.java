package org.project.service;

import org.project.db.dao.FilmDao;
import org.project.dto.FilmDTO;
import org.project.db.entity.Film;

import java.util.ArrayList;
import java.util.List;
/**

 This class represents the FilmService which is responsible for getting all the films and parsing them into FilmDTO objects.
 */
public class FilmService {
    /**

     This method gets all the films from the database and returns a list of FilmDTO objects.
     @return a List of FilmDTO objects
     */
    public List<FilmDTO> getAllFilms() {
        List<Film> films = FilmDao.getAllFilms();
        return parsingFilmInFilmDTO(films);
    }
    /**

     This method takes a list of Film objects and parses them into a list of FilmDTO objects.
     @param list a List of Film objects
     @return a List of FilmDTO objects
     */
    private List<FilmDTO> parsingFilmInFilmDTO(List<Film> list) {
        List<FilmDTO> filmDTOs = new ArrayList<>();
        for (Film film : list) {
            filmDTOs.add(new FilmDTO(film.getId(), film.getName()));
        }
        return filmDTOs;
    }
}
