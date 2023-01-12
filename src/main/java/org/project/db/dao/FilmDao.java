package org.project.db.dao;

import org.project.db.DBManager;
import org.project.db.entity.Film;
import org.project.db.entity.Genre;
import org.project.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDao{
    private static final String ADD_FILM = "INSERT INTO films (name, author, country, year, description, photo, duration) values (?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_FILM = "DELETE FROM films WHERE id=?";
    private static final String LOGIN = "SELECT * FROM users WHERE email = ?";
    private static final String FILM = "SELECT * FROM films WHERE id = ?";
    private static final String GENRES_FILM = "SELECT * FROM film_genre WHERE film_id = ?";
    private static final String ADD_GENRE_WITH_FILM = "INSERT INTO film_genre (film_id, genre_id) values (?,?) ";
    public Film insertFilm(Film film) throws SQLException {
        Connection conn = null;

        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();

            Film film1 = addFilm(film, conn);
            return film1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(conn);
        } finally {
            DBManager.getInstance().commitAndClose(conn);
        }
        return null;

    }
    public Film addFilm(Film film, Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_FILM,
                Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, film.getName());
            stmt.setString(2, film.getAuthor());
            stmt.setString(3, film.getCountry());

            stmt.setInt(4, film.getYear());
            stmt.setString(5, film.getDescription());
            stmt.setString(6, film.getPhoto());
            stmt.setTime(7, film.getDuration());
            for (Genre genre: film.getGenres()) {
                insertGenreWithFilm(genre, film, connection);
            }


            int insertAmount = stmt.executeUpdate();
            if (insertAmount > 0 ){
                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if (rs.next()){
                        film.setId(rs.getInt(1));
                    }
                }
            }

        }
        return film;

    }
    public boolean deleteUsers(Film film)  {
        Connection conn = null;

        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            PreparedStatement stmt = conn.prepareStatement(DELETE_FILM) ;
            stmt.setInt(1, film.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(null);
        }finally {
            DBManager.getInstance().commitAndClose(conn);
        }
        return true;
    }

    public boolean insertGenreWithFilm(Genre genre, Film film, Connection connection){
        try (PreparedStatement stmt = connection.prepareStatement(ADD_GENRE_WITH_FILM)){
            stmt.setInt(1, film.getId());
            stmt.setInt(2, genre.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

    public Film getFilmById(int id){
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(FILM)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Film film = new Film();
            film.setId(rs.getInt("id"));
            film.setName(rs.getString("name"));
            film.setAuthor(rs.getString("author"));
            film.setCountry(rs.getString("country"));
            film.setYear(rs.getInt("year"));
            film.setDescription(rs.getString("description"));
            film.setPhoto(rs.getString("photo"));
            film.setDuration(rs.getTime("duration"));
            film.setGenres(genresByFilm(id));
            return film;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public List<Genre> genresByFilm(int id){
        List<Genre> genres = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
        PreparedStatement stmt = conn.prepareStatement(GENRES_FILM) ){
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            Genre genre = new Genre();
            genre.setId(rs.getInt("id"));
            genre.setName(rs.getString("name"));
            genres.add(genre);
        }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genres;

    }
}
