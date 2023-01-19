package org.project.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class DBManager {
    private static DBManager instance;
    private static final Logger log = Logger.getLogger(DBManager.class);
    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }
    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");

            DataSource ds = (DataSource)envContext.lookup("jdbc/context");
            con = ds.getConnection();
        } catch (NamingException ex) {
            log.error("Cannot obtain a connection from the pool", ex);
        }
        return con;
    }

    private DBManager() {
    }


    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rollbackAndClose(Connection con ) {
        try {
            Connection connection = DBManager.getInstance().getConnectionWithDriverManager();
            connection.rollback();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnectionWithDriverManager() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cinema?serverTimezone=UTC&user=root&password=Karaimivanna2003");
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        return connection;
    }
}
