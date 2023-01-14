package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.project.db.dao.UserDao;
import org.project.db.entity.User;
import org.project.websource.Path;
import org.project.websource.command.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("email");

        log.trace("Request parameter: loging --> " + login);

        String password = request.getParameter("password");


        // error handler
        String errorMessage = "Wrong login or password!";
        String forward = Path.PAGE__ERROR_PAGE;
        request.setAttribute("error_message",errorMessage);


        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        User user = new UserDao().getUserByEmail(login);
        System.out.println(user);

        log.trace("Found in DB: user --> " + user);
        if (user == null) {
            errorMessage = "Не знайдено користувача з такою поштою!!!";
            request.setAttribute("errorMessageLogin", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE__WELCOME;
        }
        else if (!password.equals(user.getPassword())) {
            errorMessage = "Неправильний пароль";
            request.setAttribute("errorMessageLogin", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE__WELCOME;
        }
        else {
            String role = user.getRole();
            log.trace("userRole --> " + role);
            //for first enter
            ArrayList<User> users=new ArrayList<User>();
            users.add(user);

            request.setAttribute("account", users);
            // put publication the request
//            request.setAttribute("publication", publicationsForUser);
            if (Objects.equals(role, "admin")) {
                System.out.println(1234);
                forward = Path.PAGE__ACCOUNT_ADMIN;
            }else {
                forward = Path.PAGE__ACCOUNT_USER;
            }



            session.setAttribute("user", user);
            log.trace("Set the session attribute: user --> " + user);

            session.setAttribute("userRole", role);

            log.trace("Set the session attribute: userRole --> " + role);

            log.info("User " + user + " logged as " + role.toLowerCase());
        }



        log.debug("Command finished");
        return forward;
    }
}

