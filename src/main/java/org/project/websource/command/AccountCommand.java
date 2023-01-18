package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.project.dto.TicketDTO;
import org.project.dto.UserDTO;

import org.project.websource.Path;
import org.project.service.TicketService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class AccountCommand extends Command {
    private static final TicketService ticketService = new TicketService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        HttpSession sessionHttp = request.getSession();
        System.out.println(sessionHttp.getAttribute("userRole"));
        if (sessionHttp.getAttribute("userRole")== null) {
            return Path.PAGE__WELCOME;
        }else if (sessionHttp.getAttribute("userRole").equals("user")) {
            UserDTO user = (UserDTO) sessionHttp.getAttribute("user");
            request.setAttribute("user", user);
//            List<Ticket> tickets = TicketDao.getTicketByUser(user);
            List<TicketDTO> tickets = ticketService.getTicketsByUser(user.getId());
            System.out.println(tickets);
            request.setAttribute("tickets", tickets);
            return Path.PAGE__ACCOUNT_USER;
        } else{
            UserDTO user = (UserDTO) sessionHttp.getAttribute("user");
            request.setAttribute("user", user);
            return Path.PAGE__ACCOUNT_ADMIN;
        }

    }
}
