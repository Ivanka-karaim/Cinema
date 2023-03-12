package org.project.db.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        user = new User();

    }
    @Test
    void testUserEntity(){
        assertNotNull(user);
        user.setName("Ivan");
        user.setSurname("Karaim");
        user.setRole("user");
        user.setPhone_number("+380667236485");
        user.setDate_birth(new Date(2003, Calendar.JULY, 8));

        assertEquals("Ivan", user.getName());
        assertEquals("Karaim", user.getSurname());
        assertEquals("user", user.getRole());
        assertEquals("+380667236485", user.getPhone_number());
        assertEquals("Wed Jul 08 00:00:00 EEST 3903", user.getDate_birth().toString());
    }
}