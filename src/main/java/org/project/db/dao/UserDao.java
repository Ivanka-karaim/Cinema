package org.project.db.dao;

import org.project.db.DBManager;
import org.project.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**

 A class that provides methods for performing CRUD operations on the 'users' table in the database.
 */
public class UserDao {
    /**

     The SQL statement for adding a user to the 'users' table.
     */
    private static final String ADD_USER = "INSERT INTO users (name, surname, date_birth, phone_number, email, password, role) values (?, ?, ?, ?, ?, ?, ?);";
    /**

     The SQL statement for deleting a user from the 'users' table.
     */
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    /**

     The SQL statement for selecting a user from the 'users' table by email.
     */
    private static final String LOGIN = "SELECT * FROM users WHERE email = ?";
    /**

     The SQL statement for selecting a user from the 'users' table by id.
     */
    private static final String USER = "SELECT * FROM users WHERE id = ?";
    /**

     The SQL statement for selecting all users from the 'users' table.
     */

    private static final String ALL_USER = "SELECT * FROM users";
    /**

     Inserts a user into the 'users' table and returns the inserted user object.

     @param user the user to be inserted

     @return the inserted user object

     @throws SQLException if a database access error occurs
     */

    public static User insertUser(User user) throws SQLException {
        Connection conn = null;

        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            User user1 = addUser(user, conn);
            return user1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(conn);
            return null;

        } finally {
            DBManager.getInstance().commitAndClose(conn);
        }


    }
    /**

     Adds a user to the 'users' table and returns the added user object.

     @param user the user to be added

     @param connection the connection object to the database

     @return the added user object

     @throws SQLException if a database access error occurs
     */
    public static User addUser(User user, Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_USER,
                Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setDate(3, (Date) user.getDate_birth());

            stmt.setString(4, user.getPhone_number());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());
            stmt.setString(7, user.getRole());

            int insertAmount = stmt.executeUpdate();
            if (insertAmount > 0 ){
                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if (rs.next()){
                        user.setId(rs.getInt(1));
                    }
                }
            }
        }
        return user;

    }
    /**

     Deletes a user from the 'users' table by id.

     @param user the user to be deleted

     @return true if the user is deleted, otherwise false
     */
    public static boolean deleteUsers(User user)  {
        Connection conn = null;

        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(DELETE_USER) ;
            stmt.setInt(1, user.getId());
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

     This method retrieves a User object by email from the database.
     @param email the email of the User to retrieve.
     @return a User object representing the user with the specified email, or null if the user cannot be found.
     */
    public static User getUserByEmail(String email){
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
        PreparedStatement stmt = conn.prepareStatement(LOGIN)) {
            System.out.println(email);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            User user = new User();
            rs.next();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setDate_birth(rs.getDate("date_birth"));
            user.setPhone_number(rs.getString("phone_number"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            System.out.println(543);
            return null;
        }

    }
    /**

     This method retrieves a list of all users from the database.
     @return a List of User objects representing all users in the database, or null if an error occurs.
     */
    public static List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(ALL_USER)) {
            ResultSet rs = stmt.executeQuery();


            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setDate_birth(rs.getDate("date_birth"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }

        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            System.out.println(543);
            return null;
        }
        return users;

    }
    /**

     This method retrieves a User object by ID from the database.
     @param id the ID of the User to retrieve.
     @return a User object representing the user with the specified ID, or null if the user cannot be found.
     @throws SQLException if an error occurs while retrieving the user from the database.
     */

    public static User getUserById(int id) throws SQLException{
        try (Connection conn = DBManager.getInstance().getConnectionWithDriverManager();
             PreparedStatement stmt = conn.prepareStatement(USER)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setDate_birth(rs.getDate("date_birth"));
            user.setPhone_number(rs.getString("phone_number"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }
}
