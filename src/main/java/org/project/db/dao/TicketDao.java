package org.project.db.dao;

import org.project.db.DBManager;
import org.project.db.entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**

 The TicketDao class provides CRUD operations for the tickets table in the database.
 */
public class TicketDao {
    /**

     The SQL query to delete all tickets for a given session.
     */
    private static final String DELETE_TICKETS_SESSION = "DELETE FROM tickets WHERE session_id =?";
    /**

     The SQL query to update the user_id field of a ticket.
     */
    private static final String UPDATE_TICKET = "UPDATE tickets SET user_id=? WHERE id=?";
    /**

     The SQL query to add a new ticket to the database.
     */
    private static final String ADD_SESSION = "INSERT INTO tickets (place, session_id) values (?, ?);";
    /**

     The SQL query to get all tickets for a given session.
     */
    private static final String GET_TICKETS_SESSION = "SELECT * FROM tickets WHERE session_id=?";
    /**

     The SQL query to get a ticket by its id.
     */
    private static final String TICKET = "SELECT * FROM tickets WHERE id=?";
    /**

     The SQL query to get all tickets for a given user, sorted by session timestamp.
     */
    private static final String TICKET_USER = "SELECT * FROM tickets, session WHERE tickets.user_id=? and tickets.session_id=session.id ORDER BY session.timestamp";
    /**

     Returns a list of all tickets belonging to a given user, sorted by session timestamp.

     @param id the user id to search for

     @return a list of Ticket objects
     */
    public static List<Ticket> getTicketByUser(int id){
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(TICKET_USER)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {

                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setPlace(rs.getInt("place"));
                ticket.setSession(SessionDao.getSessionByID(rs.getInt("session_id")));
                int user1 = rs.getInt("user_id");
                if (user1 != 0) {
                    ticket.setUser(UserDao.getUserById(user1));
                } else {
                    ticket.setUser(null);
                }
                tickets.add(ticket);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tickets;
    }
    /**

     Deletes all tickets belonging to a given session.

     @param id the session id to delete tickets for

     @return true if the operation was successful, false otherwise
     */
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
    /**

     Returns a Ticket object for a given ticket id.
     @param id the ticket id to search for
     @return a Ticket object, or null if no ticket was found
     */
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
    /**

     Inserts a new ticket into the database.
     @param ticket The ticket object to insert into the database.
     @return The ticket object that was inserted into the database, including the generated ID.
     */
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
    /**

     Adds a ticket to the database with the given connection.
     @param ticket The ticket object to add to the database.
     @param connection The connection to the database.
     @return The ticket object that was added to the database, including the generated ID.
     @throws SQLException If an SQL exception occurs.
     */
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
    /**

     Gets a list of all tickets associated with a given session ID.
     @param id The ID of the session to get tickets for.
     @return A list of ticket objects associated with the given session ID.
     */
    public static List<Ticket> get_tickets(int id){
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(GET_TICKETS_SESSION)) {
            stmt.setInt(1, id);
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
    /**

     Updates the user ID associated with a given ticket ID in the database.
     @param id_ticket The ID of the ticket to update.
     @param id_user The ID of the user to associate with the ticket.
     @return True if the update was successful, false otherwise.
     */
    public static boolean updateTicket(int id_ticket, int id_user){
        Connection conn = null;
        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_TICKET) ;
            stmt.setInt(1, id_user);
            stmt.setInt(2, id_ticket);
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
