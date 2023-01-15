package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.project.db.dao.TicketDao;
import org.project.db.dao.UserDao;
import org.project.db.entity.Ticket;
import org.project.db.entity.User;
import org.project.websource.Path;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class AccountCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        HttpSession sessionHttp = request.getSession();
        System.out.println(sessionHttp.getAttribute("userRole"));
        if (sessionHttp.getAttribute("userRole")== null) {
            return Path.PAGE__WELCOME;
        }else if (sessionHttp.getAttribute("userRole").equals("user")) {
            System.out.println(133655);
            User user = (User) sessionHttp.getAttribute("user");
            request.setAttribute("user", user);
            List<Ticket> tickets = TicketDao.getTicketByUser(user);
            System.out.println(tickets);
            request.setAttribute("tickets", tickets);
            return Path.PAGE__ACCOUNT_USER;
        } else{
            User user = (User) sessionHttp.getAttribute("user");
            request.setAttribute("user", user);
            return Path.PAGE__ACCOUNT_ADMIN;
        }

    }
}
