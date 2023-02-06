package daoTest;

import org.junit.Test;
import org.project.db.dao.FilmDao;
import org.project.db.entity.Film;

import java.util.List;
import static org.junit.Assert.assertEquals;

public class FilmDaoTest {

    @Test
    public void testFilmDao(){
        List<Film> films = FilmDao.getAllFilms();

        assertEquals(2, films.size());

        Film film1 = FilmDao.getFilmById(films.get(0).getId());
        assert film1 != null;
        assertEquals(film1.getId(), films.get(0).getId());

        assertEquals(film1.getName(), films.get(0).getName());

    }
}
