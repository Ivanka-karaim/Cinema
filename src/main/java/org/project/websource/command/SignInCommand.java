package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.project.db.dao.UserDao;
import org.project.db.dto.UserDTO;
import org.project.db.entity.User;
import org.project.websource.Path;
import org.project.websource.service.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class SignInCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private static final UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String errorMessage;
        HttpSession session = request.getSession();
        String forward = Path.PAGE__ERROR_PAGE;
        UserDTO user =new UserDTO(0, request.getParameter("name"),request.getParameter("surname"),  request.getParameter("email"), "user", request.getParameter("password"),request.getParameter("phone_number"));

//        if ( password == null || email == null  || password.isEmpty() || email.isEmpty()) {
//            errorMessage = "Login/password cannot be empty";
//            request.setAttribute("errorMessage", errorMessage);
//            log.error("errorMessage --> " + errorMessage);
//            return forward;
//        }
//        if (!phone_number.matches("^\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$")){
//            errorMessage="Phone number wrong";
//            request.setAttribute("errorMessage", errorMessage);
//            log.error("errorMessage --> " + errorMessage);
//            return forward;
//        }
//        if (!email.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")){
//            errorMessage="Email wrong";
//            request.setAttribute("errorMessage", errorMessage);
//            log.error("errorMessage --> " + errorMessage);
//            return forward;
//        }

//        user.setId(1);
//        user.setName(name);
//        user.setSurname(surname);
//        user.setEmail(email);
//        user.setPhone_number(phone_number);
//        user.setPassword(password);
//        user.setRole("user");


//            new UserDao().insertUser(user);
        try {
            userService.createUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("user",user);
        session.setAttribute("userRole", "user");
        forward=Path.PAGE__ACCOUNT_USER;
        request.setAttribute("email", user.email);
        return forward;
    }
}
