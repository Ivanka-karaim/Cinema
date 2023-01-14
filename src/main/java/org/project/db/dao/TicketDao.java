package org.project.db.dao;

import org.project.db.DBManager;
import org.project.db.entity.Session;
import org.project.db.entity.Ticket;
import org.project.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    private static final String DELETE_TICKETS_SESSION = "DELETE FROM tickets WHERE session_id =?";
    private static final String UPDATE_TICKET = "UPDATE tickets SET user_id=? WHERE id=?";
    private static final String ADD_SESSION = "INSERT INTO tickets (place, session_id) values (?, ?);";
    private static final String GET_TICKETS_SESSION = "SELECT * FROM tickets WHERE session_id=?";
    private static final String TICKET = "SELECT * FROM tickets WHERE id=?";

    public static boolean deleteTicketsBySession(int id)  {
        Connection conn = null;
        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            PreparedStatement stmt = conn.prepareStatement(DELETE_TICKETS_SESSION) ;
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
    public static Ticket getTicketById(int id){
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(TICKET)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setPlace(rs.getInt("place"));
            ticket.setSession(SessionDao.getSessionByID(rs.getInt("session_id")));
            int user = rs.getInt("user_id");
            if (user != 0){
                ticket.setUser(UserDao.getUserById(user));
            }else{
                ticket.setUser(null);
            }
            return ticket;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static Ticket insertTicket(Ticket ticket) {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            Ticket ticket1 = addTicket(ticket, connection);
            return ticket1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(connection);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return null;
    }

    public static Ticket addTicket(Ticket ticket, Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_SESSION,
                Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, ticket.getPlace());
            stmt.setInt(2,ticket.getSession().getId());

            int insertAmount = stmt.executeUpdate();
            if (insertAmount > 0 ){
                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if (rs.next()){
                        ticket.setId(rs.getInt(1));
                    }
                }
            }
        }
        return ticket;

    }
    public static List<Ticket> get_tickets(Session session){
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(GET_TICKETS_SESSION)) {
            stmt.setInt(1, session.getId());
            ResultSet rs = stmt.executeQuery();
            while( rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setPlace(rs.getInt("place"));
                ticket.setSession(SessionDao.getSessionByID(rs.getInt("session_id")));
                int user = rs.getInt("user_id");
                if (user != 0){
                    ticket.setUser(UserDao.getUserById(user));
                }else{
                    ticket.setUser(null);
                }
                tickets.add(ticket);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tickets;
    }
    public static boolean updateTicket(Ticket ticket, User user){
        Connection conn = null;
        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_TICKET) ;
            stmt.setInt(1, user.getId());
            stmt.setInt(2, ticket.getId());
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
