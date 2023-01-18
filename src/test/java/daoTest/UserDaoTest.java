package daoTest;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;


import org.junit.Test;
import org.project.db.dao.UserDao;
import org.project.db.entity.User;

import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDaoTest {



    @Test
    public void testUserDaoInsertDelete() {
        User newUser = new User();
        newUser.setId(1);
        newUser.setName("Ivanna");
        newUser.setSurname("Karaim");
        newUser.setEmail("karaim@gmail.com");
        newUser.setPassword("1111");
        newUser.setRole("user");
        try {
            User user = UserDao.insertUser(newUser);
        assert user != null;
        User user1 = UserDao.getUserById(user.getId());
        assert user1 != null;
        assertEquals(user.getId(), user1.getId());
        UserDao.deleteUsers(user1);
        UserDao.getUserById(user1.getId());

        }catch (SQLException e){
            e.printStackTrace();
        }







    }
}
