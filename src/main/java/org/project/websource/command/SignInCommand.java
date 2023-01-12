package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.project.db.dao.UserDao;
import org.project.db.entity.User;
import org.project.websource.Path;

import java.io.IOException;
import java.sql.SQLException;

public class SignInCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String errorMessage;
        HttpSession session = request.getSession();
        String forward = Path.PAGE__ERROR_PAGE;
        User user =new User();


        String email = request.getParameter("email");
        log.trace("Request parameter: loging --> " + email);
        String name = request.getParameter("name");
        log.trace("Request parameter: loging --> " + name);
        String surname = request.getParameter("surname");
        log.trace("Request parameter: loging --> " + surname);
        String phone_number = request.getParameter("phone_number");
        log.trace("Request parameter: loging --> " + phone_number);

        String password = request.getParameter("password");
        log.trace("Request parameter: loging --> " + password);
        if ( password == null || email == null  || password.isEmpty() || email.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        if (!phone_number.matches("^\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$")){
            errorMessage="Phone number wrong";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        if (!email.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")){
            errorMessage="Email wrong";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        user.setId(1);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhone_number(phone_number);
        user.setPassword(password);
        user.setRole("user");

        try {
            new UserDao().insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("user",user);
        session.setAttribute("userRole", "user");
        forward=Path.PAGE__ACCOUNT_USER;
        request.setAttribute("email", email);
        return forward;
    }
}
