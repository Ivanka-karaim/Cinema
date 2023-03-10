package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.project.dto.TicketDTO;
import org.project.dto.UserDTO;
import org.project.websource.Path;
import org.project.service.TicketService;
import org.project.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LoginCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private static final TicketService ticketService = new TicketService();
    private static final UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");
        HttpSession sessionHttp = request.getSession();
        String forward;
        String login = request.getParameter("email");
        log.trace("Request parameter: email --> " + login);

        String password = request.getParameter("password");
        UserDTO user = userService.getUserByEmail(login);

        log.trace("Found in DB: user --> " + user);
        if (user == null) {
            String errorMessage = "error_user_email";
//            request.setAttribute("error", errorMessage);
            sessionHttp.setAttribute("error", errorMessage);

            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE__WELCOME;

        }
        else if (!password.equals(user.getPassword())) {
            String errorMessage = "error_user_password";
//            request.setAttribute("error", errorMessage);
            sessionHttp.setAttribute("error", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE__WELCOME;


        }
        else {
            String role = user.getRole();
            log.trace("userRole --> " + role);
            if (!Objects.equals(role, "admin")) {
                List<TicketDTO> tickets = ticketService.getTicketsByUser(user.getId());
                request.setAttribute("tickets", tickets);
            }
            sessionHttp.removeAttribute("error");
            sessionHttp.setAttribute("user", user);
            log.trace("Set the session attribute: user --> " + user);
            sessionHttp.setAttribute("userRole", role);
            log.trace("Set the session attribute: userRole --> " + role);
            log.info("User " + user + " logged as " + role.toLowerCase());
        }
        log.debug("Command finished");
        return request.getRequestURL().toString();
    }
}

