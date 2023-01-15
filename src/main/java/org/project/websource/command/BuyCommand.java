package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.project.db.dao.TicketDao;
import org.project.db.entity.Ticket;
import org.project.db.entity.User;
import org.project.websource.Path;

import java.io.IOException;
import java.text.ParseException;

public class BuyCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("userRole"));
        if (session.getAttribute("userRole")!= null) {
            TicketDao.updateTicket(TicketDao.getTicketById(id), (User) session.getAttribute("user"));
        }
        return Path.PAGE__ACCOUNT_USER;
    }
}
