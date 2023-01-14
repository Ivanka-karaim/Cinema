package org.project.db.dao;

import org.project.db.DBManager;
import org.project.db.entity.Film;
import org.project.db.entity.Session;
import org.project.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDao {
    private static final String ALL_SESSION ="SELECT * FROM session WHERE timestamp > now() ORDER BY timestamp ASC";
    private static  final  String SESSION_PAGINATION = "SELECT * FROM session WHERE timestamp > now() ORDER BY timestamp ASC LIMIT ?,?";
    private static final String ADD_SESSION = "INSERT INTO session (timestamp, price, film_id) values (?, ?, ?);";
    private static final String DELETE_SESSION = "DELETE FROM session WHERE id=?";
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

    public Session insertSession(Session session) {
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

    public Session addSession(Session session, Connection connection) throws SQLException {
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
