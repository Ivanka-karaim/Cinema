package org.project.db.dao;

import org.project.db.DBManager;
import org.project.db.entity.Film;
import org.project.db.entity.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**

 The FilmDao class is responsible for database interactions related to the Film entity.
 It contains methods for adding, deleting, and retrieving films from the database.
 */
public class FilmDao{
    /**
    The SQL query for adding a film to the database.
    */
    private static final String ADD_FILM = "INSERT INTO films (name, author, country, year, description, photo, duration) values (?, ?, ?, ?, ?, ?, ?);";
    /**
    The SQL query for deleting a film from the database.
    */
    private static final String DELETE_FILM = "DELETE FROM films WHERE id=?";
    /**
    The SQL query for retrieving a user from the database by email.
    */
    private static final String LOGIN = "SELECT * FROM users WHERE email = ?";
    /**
    The SQL query for retrieving a film from the database by ID.
    */
    private static final String FILM = "SELECT * FROM films WHERE id = ?";
    /**
    The SQL query for retrieving all genres associated with a film from the database.
    */
    private static final String GENRES_FILM = "SELECT * FROM film_genre WHERE film_id = ?";
    /**
    The SQL query for adding a genre associated with a film to the database.
    */
    private static final String ADD_GENRE_WITH_FILM = "INSERT INTO film_genre (film_id, genre_id) values (?,?) ";
    /**
    The SQL query for retrieving all films from the database.
    */
    private static final String FILMS = "SELECT * FROM films";
    /**

     Inserts a film into the database.

     @param film the film to insert into the database.

     @return the inserted film object.

     @throws SQLException if a database access error occurs.
     */
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
    /**

     Adds a film to the database.

     @param film the film to add to the database.

     @param connection the connection object to the database.

     @return the added film object.

     @throws SQLException if a database access error occurs.
     */
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
    /**

     This method deletes a film from the database

     @param film the film to be deleted

     @return true if the film was successfully deleted, false otherwise
     */
    public boolean deleteFilm(Film film)  {
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
    /**
    This method inserts a genre associated with a film into the database
    @param genre the genre to be inserted
    @param film the film to which the genre is to be associated
    @param connection the database connection to be used
     @return true if the genre was successfully inserted, false otherwise
     */


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
    /**

     This method retrieves a film from the database by its ID
     @param id the ID of the film to be retrieved
     @return the film if it exists in the database, null otherwise
     */
    public static Film getFilmById(int id){
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
    /**

     This method retrieves all genres associated with a film from the database
     @param id the ID of the film whose genres are to be retrieved
     @return a list of all genres associated with the specified film
     */
    public static List<Genre> genresByFilm(int id){
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
    /**

     This method retrieves all films from the database

     @return a list of all films
     */
    public static List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(FILMS); ResultSet rs = stmt.executeQuery();){
            while(rs.next()){
                Film film = new Film();
                film.setId(rs.getInt("id"));
                film.setName(rs.getString("name"));
                film.setAuthor(rs.getString("author"));
                film.setCountry(rs.getString("country"));
                film.setYear(rs.getInt("year"));
                film.setDescription(rs.getString("description"));
                film.setPhoto(rs.getString("photo"));
                film.setDuration(rs.getTime("duration"));
                film.setGenres(genresByFilm(film.getId()));
                films.add(film);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return films;
    }
}
