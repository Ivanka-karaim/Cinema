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

public class BuyCommand extends Command{
    private static final Logger log = Logger.getLogger(BuyCommand.class);
    private static final TicketService ticketService = new TicketService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        log.debug("Command starts");
        int id = Integer.parseInt(request.getParameter("place"));

        HttpSession sessionHttp = request.getSession();
        log.trace("Session parameter: userRole --> " + sessionHttp.getAttribute("userRole"));

        String number_cart = request.getParameter("number_cart");
        String date = request.getParameter("date");
        String cvv = request.getParameter("cvv");
        if (!number_cart.matches("(\\d{4}([-]|)\\d{4}([-]|)\\d{4}([-]|)\\d{4})") || !date.matches("^\\d{2}/\\d{2}$") || !cvv.matches("\\d{3}")){
            System.out.println(number_cart);
            System.out.println(!number_cart.matches("(\\d{4}([-]|)\\d{4}([-]|)\\d{4}([-]|)\\d{4})"));
            System.out.println(!date.matches("^\\d{2}+/\\d{2}$"));
            System.out.println(!cvv.matches("^\\d{3}$"));
            request.getSession().setAttribute("error", "Wrong bank card");
            return "error";
        }

        if (sessionHttp.getAttribute("userRole")!= null) {
            UserDTO user = (UserDTO) sessionHttp.getAttribute("user");
            ticketService.updateTicket(id, user.getId());
            List<TicketDTO> tickets = ticketService.getTicketsByUser(user.getId());
            request.setAttribute("tickets", tickets);
        }
        log.debug("Command finished");

        return request.getRequestURL().toString();
    }
}
