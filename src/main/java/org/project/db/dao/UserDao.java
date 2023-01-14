package org.project.db.dao;

import org.project.db.DBManager;
import org.project.db.entity.User;

import java.sql.*;

public class UserDao {
    private static final String ADD_USER = "INSERT INTO users (name, surname, date_birth, phone_number, email, password, role) values (?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String LOGIN = "SELECT * FROM users WHERE email = ?";
    private static final String USER = "SELECT * FROM users WHERE id = ?";

    public User insertUser(User user) throws SQLException {
        Connection conn = null;

        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            User user1 = addUser(user, conn);
            return user1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(conn);
        } finally {
            DBManager.getInstance().commitAndClose(conn);
        }
        return null;

    }
    public User addUser(User user, Connection connection) throws SQLException {
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
    public boolean deleteUsers(User user)  {
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
    public User getUserByEmail(String email){
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

    public static User getUserById(int id){
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
