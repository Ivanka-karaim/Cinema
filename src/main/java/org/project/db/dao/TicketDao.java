package org.project.db.dao;

import org.project.db.DBManager;
import org.project.db.entity.Session;
import org.project.db.entity.Ticket;

import java.sql.*;

public class TicketDao {
    private static final String DELETE_TICKETS_SESSION = "DELETE FROM tickets WHERE session_id =?";
    private static final String ADD_SESSION = "INSERT INTO tickets (place, session_id) values (?, ?);";


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
}
