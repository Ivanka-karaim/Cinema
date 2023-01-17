package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.project.db.dao.TicketDao;
import org.project.db.dto.TicketDTO;
import org.project.db.dto.UserDTO;
import org.project.db.entity.Ticket;
import org.project.db.entity.User;
import org.project.websource.Path;
import org.project.websource.service.TicketService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class BuyCommand extends Command{
    private static final TicketService ticketService = new TicketService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession sessionHttp = request.getSession();
        System.out.println(sessionHttp.getAttribute("userRole"));
        if (sessionHttp.getAttribute("userRole")!= null) {
            UserDTO user = (UserDTO) sessionHttp.getAttribute("user");
//            TicketDao.updateTicket(TicketDao.getTicketById(id), (User) session.getAttribute("user"));
            ticketService.updateTicket(id, user.getId());
            List<TicketDTO> tickets = ticketService.getTicketsByUser(user.getId());
            request.setAttribute("tickets", tickets);
        }

        return Path.PAGE__ACCOUNT_USER;
    }
}
