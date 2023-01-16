package org.project.websource.service;

import org.project.db.dao.TicketDao;
import org.project.db.dao.UserDao;
import org.project.db.dto.TicketDTO;
import org.project.db.entity.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TicketService {


    public List<TicketDTO> getTicketsByUser(int id) {
        List<Ticket> tickets = TicketDao.getTicketByUser(Objects.requireNonNull(UserDao.getUserById(id)));
        return parsingTicketInTicketDTO(tickets);
    }
    public boolean updateTicket(int id, int id_user){
        return TicketDao.updateTicket(id,id_user);
    }

    private List<TicketDTO> parsingTicketInTicketDTO(List<Ticket> list) {
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : list) {
            ticketDTOs.add(new TicketDTO(ticket.getId(), ticket.getPlace(),  ticket.getSession().getFilm().getName(), ticket.getSession().getPrice(), ticket.getSession().getTimestamp(), ticket.getUser()));


        }
        return ticketDTOs;
    }

    public List<TicketDTO> getTickets(int id_session) {
        List<Ticket> tickets = TicketDao.get_tickets(id_session);
        return parsingTicketInTicketDTO(tickets);

    }
}
