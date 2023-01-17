package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.project.db.dao.TicketDao;
import org.project.db.dao.UserDao;
import org.project.db.dto.TicketDTO;
import org.project.db.dto.UserDTO;
import org.project.db.entity.Ticket;
import org.project.db.entity.User;
import org.project.websource.Path;
import org.project.websource.command.Command;
import org.project.websource.service.TicketService;
import org.project.websource.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private static final TicketService ticketService = new TicketService();
    private static final UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionHttp = request.getSession();
        String forward;
        String login = request.getParameter("email");

        String password = request.getParameter("password");
        UserDTO user = userService.getUserByEmail(login);
        System.out.println(user);

        log.trace("Found in DB: user --> " + user);
        if (user == null) {
            String errorMessage = "Не знайдено користувача з такою поштою!!!";
            request.setAttribute("error", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE__WELCOME;
        }
        else if (!password.equals(user.getPassword())) {
            String errorMessage = "Неправильний пароль";
            request.setAttribute("error", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE__WELCOME;
        }
        else {
            String role = user.getRole();
            log.trace("userRole --> " + role);
            if (Objects.equals(role, "admin")) {
                forward = Path.PAGE__ACCOUNT_ADMIN;
            }else {
//                List<Ticket> tickets = TicketDao.getTicketByUser(user);
                List<TicketDTO> tickets = ticketService.getTicketsByUser(user.getId());
                request.setAttribute("tickets", tickets);
                forward = Path.PAGE__ACCOUNT_USER;
            }
            sessionHttp.setAttribute("user", user);
            log.trace("Set the session attribute: user --> " + user);
            sessionHttp.setAttribute("userRole", role);
            log.trace("Set the session attribute: userRole --> " + role);
            log.info("User " + user + " logged as " + role.toLowerCase());
        }
        log.debug("Command finished");
        return forward;
    }
}

