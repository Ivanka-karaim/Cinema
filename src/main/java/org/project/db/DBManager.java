package org.project.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
/**

 The DBManager class contains methods for obtaining a database connection and managing transactions.
 */
public class DBManager {
    /**

     The static instance of the DBManager class.
     */
    private static DBManager instance;
    /**

     The logger instance for the DBManager class.
     */
    private static final Logger log = Logger.getLogger(DBManager.class);
    /**

     Returns the static instance of the DBManager class, creating it if necessary.
     @return the DBManager instance.
     */
    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }
    /**

     Returns a connection to the database using JNDI, or null if a connection cannot be obtained.

     @return a Connection object representing a connection to the database, or null if a connection cannot be obtained.

     @throws SQLException if an error occurs while obtaining a connection from the pool.
     */
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

    /**

     Commits the transaction associated with the given connection and closes the connection.
     @param con the  Connection object to commit and close.
     */
    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**

     Rolls back the transaction associated with the given connection and closes the connection.
     @param con the Connection object to roll back and close.
     */
    public void rollbackAndClose(Connection con ) {
        try {
            Connection connection = DBManager.getInstance().getConnectionWithDriverManager();
            connection.rollback();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**

     Returns a connection to the database using the DriverManager API.

     @return a Connection object representing a connection to the database.

     @throws SQLException if an error occurs while obtaining a connection from the DriverManager.
     */
    public Connection getConnectionWithDriverManager() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cinema?serverTimezone=UTC&user=root&password=123456");
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        return connection;
    }
}
