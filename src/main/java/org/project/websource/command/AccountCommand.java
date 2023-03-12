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

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class AccountCommand extends Command {
    private static final Logger log = Logger.getLogger(AccountCommand.class);
    private static final TicketService ticketService = new TicketService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        log.debug("Command starts");
        HttpSession sessionHttp = request.getSession();
        sessionHttp.removeAttribute("error");
        log.trace("Session parameter: userRole --> " + sessionHttp.getAttribute("userRole"));
        if (sessionHttp.getAttribute("userRole")== null) {
            return Path.PAGE__WELCOME;
        }else if (sessionHttp.getAttribute("userRole").equals("user")) {
            UserDTO user = (UserDTO) sessionHttp.getAttribute("user");
            List<TicketDTO> tickets = ticketService.getTicketsByUser(user.getId());
            log.trace("ticketList for User --> " + tickets);
            request.setAttribute("tickets", tickets);
            return Path.PAGE__ACCOUNT_USER;
        } else{
            return Path.PAGE__ACCOUNT_ADMIN;
        }
    }
}
