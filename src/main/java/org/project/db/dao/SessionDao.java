package org.project.db.dao;

import org.project.db.DBManager;
import org.project.db.entity.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**

 The SessionDao class is responsible for database interactions related to the Session entity.
 It contains methods for adding, deleting, and retrieving sessions from the database.
 */
public class SessionDao {
    /**
     The SQL query for retrieving all sessions from the database.
     */
    private static final String ALL_SESSION ="SELECT * FROM session WHERE timestamp > now() ORDER BY timestamp ASC";
    /**
     The SQL query for retrieving all sessions order by time and where film from the database.
     */
    private static final String ALL_SESSION_FILM ="SELECT * FROM session WHERE timestamp > now() and film_id=? ORDER BY timestamp ASC";
    /**
     The SQL query for retrieving all sessions order by time and sorted by time from the database.
     */
    private static final String ALL_SESSION_DATE ="SELECT * FROM session WHERE timestamp > ? and timestamp <? ORDER BY timestamp ASC";
    /**
     The SQL query for retrieving all sessions order by time with limit from the database.
     */
    private static  final  String SESSION_PAGINATION = "SELECT * FROM session WHERE timestamp > now() ORDER BY timestamp ASC LIMIT ?,?";
    /**
     The SQL query for adding a session to the database.
     */
    private static final String ADD_SESSION = "INSERT INTO session (timestamp, price, film_id) values (?, ?, ?);";
    /**
     The SQL query for deleting a film to the database.
     */
    private static final String DELETE_SESSION = "DELETE FROM session WHERE id=?";
    /**
     The SQL query for getting a session to the database.
     */
    private static final String GET_SESSION_ID = "SELECT * FROM session WHERE id=?";
    /**
     The SQL query for getting all sessions sorted name to the database.
     */
    private static final String ALL_SESSION_SORT_NAME = "SELECT * FROM session, films WHERE session.timestamp > now() and session.film_id= films.id ORDER BY films.name, session.timestamp ASC";
    /**
     The SQL query for getting all sessions sorted count free places to the database.
     */
    private static final String ALL_SESSION_SORT_COUNT= "SELECT * FROM session, tickets WHERE tickets.session_id= session.id and session.timestamp> now() GROUP BY tickets.session_id ORDER BY COUNT(tickets.user_id) ASC";
    /**

     This method returns a list of all sessions for a given film.
     @param film_id The id of the film for which to retrieve sessions.
     @return A List of Session objects.
     */
    public static List<Session> getSessionsByFilm(int film_id){
        List<Session> sessions = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(ALL_SESSION_FILM)) {
            stmt.setInt(1, film_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setTimestamp(rs.getTimestamp("timestamp"));
                session.setPrice(rs.getDouble("price"));
                session.setFilm(new FilmDao().getFilmById(rs.getInt("film_id")));
                sessions.add(session);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sessions;
    }
    /**

     This method returns a Session object by its id.
     @param id The id of the session to retrieve.
     @return A Session object.
     */
    public static Session getSessionByID(int id){
            try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
                 PreparedStatement stmt = conn.prepareStatement(GET_SESSION_ID)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                Session session = new Session();
                session.setTimestamp(rs.getTimestamp("timestamp"));
                session.setId(rs.getInt("id"));
                session.setPrice(rs.getDouble("price"));
                session.setFilm(FilmDao.getFilmById(rs.getInt("film_id")));
                return session;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
    }
    /**

     This method returns a list of all sessions.
     @return A List of Session objects.
     */
    public static List<Session> getAllSessions(){
        List<Session> sessions = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(ALL_SESSION)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setTimestamp(rs.getTimestamp("timestamp"));
                session.setPrice(rs.getDouble("price"));
                session.setFilm(new FilmDao().getFilmById(rs.getInt("film_id")));
                sessions.add(session);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sessions;

    }
    /**

     This method returns a list of sessions for pagination.
     @param a The starting index for the pagination.
     @param b The number of sessions to retrieve.
     @return A List of Session objects.
     */
    public static List<Session> getAllSessionsForPagination(int a, int b){
        List<Session> sessions = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(ALL_SESSION+" LIMIT ?, ?")) {
            stmt.setInt(1, a);
            stmt.setInt(2, b);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setTimestamp(rs.getTimestamp("timestamp"));
                session.setPrice(rs.getDouble("price"));
                session.setFilm(new FilmDao().getFilmById(rs.getInt("film_id")));
                sessions.add(session);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sessions;

    }
    /**

     Returns a list of sessions sorted by count, starting at index 'a' and with a maximum of 'b' results.
     @param a The starting index for the query.
     @param b The maximum number of results to return.
     @return A list of sessions sorted by count, starting at index 'a' and with a maximum of 'b' results.
     */
    public static List<Session> getAllSessionsSortCount(int a, int b){
        List<Session> sessions = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(ALL_SESSION_SORT_COUNT+" LIMIT ?,?")) {
            stmt.setInt(1,a);
            stmt.setInt(2,b);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setTimestamp(rs.getTimestamp("timestamp"));
                session.setPrice(rs.getDouble("price"));
                session.setFilm(new FilmDao().getFilmById(rs.getInt("film_id")));
                sessions.add(session);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sessions;

    }
    /**

     Returns a list of sessions sorted by name, starting at index 'a' and with a maximum of 'b' results.
     @param a The starting index for the query.
     @param b The maximum number of results to return.
     @return A list of sessions sorted by name, starting at index 'a' and with a maximum of 'b' results.
     */
    public static List<Session> getAllSessionsSortName(int a, int b){
        List<Session> sessions = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(ALL_SESSION_SORT_NAME+" LIMIT ?,?")) {
            stmt.setInt(1,a);
            stmt.setInt(2, b);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setTimestamp(rs.getTimestamp("timestamp"));
                session.setPrice(rs.getDouble("price"));
                session.setFilm(new FilmDao().getFilmById(rs.getInt("film_id")));
                sessions.add(session);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sessions;

    }
    /**

     Returns a list of sessions that took place between the specified timestamps.
     @param timestamp1 The start timestamp for the query.
     @param timestamp2 The end timestamp for the query.
     @return A list of sessions that took place between the specified timestamps.
     */
    public static List<Session> getAllSessionsWhereDate(Timestamp timestamp1, Timestamp timestamp2){
        List<Session> sessions = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(ALL_SESSION_DATE)) {

            stmt.setTimestamp(1, timestamp1);

            stmt.setTimestamp(2, timestamp2);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setTimestamp(rs.getTimestamp("timestamp"));
                session.setPrice(rs.getDouble("price"));
                session.setFilm(new FilmDao().getFilmById(rs.getInt("film_id")));
                sessions.add(session);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sessions;

    }
    /**

     Returns a list of sessions starting at index 'start' and with a maximum of 'end' results.
     @param start The starting index for the query.
     @param end The maximum number of results to return.
     @return A list of sessions starting at index 'start' and with a maximum of 'end' results.
     */

    public List<Session> getSessionsPagination(int start, int end){
        List<Session> sessions = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(SESSION_PAGINATION)) {
            stmt.setInt(1, start);
            stmt.setInt(2, end);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setTimestamp(rs.getTimestamp("timestamp"));
                session.setPrice(rs.getDouble("price"));
                session.setFilm(new FilmDao().getFilmById(rs.getInt("film_id")));
                sessions.add(session);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sessions;


    }
    /**

     Inserts the specified session into the database.
     @param session The session to insert.
     @return The inserted session.
     */
    public static Session insertSession(Session session) {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            Session session1 = addSession(session, connection);
            return session1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(connection);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return null;
    }
    /**

     Adds the specified session to the database using the provided database connection.
     @param session The session to add.
     @param connection The database connection to use.
     @return The added session.
     @throws SQLException if an error occurs while executing the SQL statement.
     */
    public static Session addSession(Session session, Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_SESSION,
                Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, session.getTimestamp());
            stmt.setDouble(2,session.getPrice());
            stmt.setInt(3, session.getFilm().getId());


            int insertAmount = stmt.executeUpdate();
            if (insertAmount > 0 ){
                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if (rs.next()){
                        session.setId(rs.getInt(1));
                    }
                }
            }
        }
        return session;

    }
    /**

     Deletes the session with the specified ID from the database.
     @param id The ID of the session to delete.
     @return true if the session was successfully deleted, false otherwise.
     */
    public static boolean deleteSession(int id){
        Connection conn = null;

        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            PreparedStatement stmt = conn.prepareStatement(DELETE_SESSION) ;
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(null);
        }finally {
            DBManager.getInstance().commitAndClose(conn);
        }
        return true;
    }


}
