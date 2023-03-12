package org.project.service;

import org.project.db.dao.TicketDao;
import org.project.dto.TicketDTO;
import org.project.db.entity.Ticket;

import java.util.ArrayList;
import java.util.List;
/**

 This class provides a set of methods to retrieve and manipulate information related to tickets.
 */
public class TicketService {

    /**

     Retrieves a list of ticket DTOs corresponding to a given user ID.
     @param id the ID of the user to retrieve tickets for
     @return a list of ticket DTOs corresponding to the user
     */
    public List<TicketDTO> getTicketsByUser(int id) {
        List<Ticket> tickets = TicketDao.getTicketByUser(id);
        return parsingTicketInTicketDTO(tickets);
    }/**

     Retrieves a ticket DTO corresponding to a given ticket ID.
     @param id the ID of the ticket to retrieve
     @return a ticket DTO corresponding to the ticket ID
     */

    public TicketDTO getTicketById(int id){
        Ticket ticket = TicketDao.getTicketById(id);
        List<Ticket> t = new ArrayList<>();
        t.add(ticket);
        return parsingTicketInTicketDTO(t).get(0);
    }
    /**

     Updates a ticket's user ID.
     @param id the ID of the ticket to update
     @param id_user the ID of the user to set for the ticket
     @return true if the update was successful, false otherwise
     */
    public boolean updateTicket(int id, int id_user){
        return TicketDao.updateTicket(id,id_user);
    }
    /**

     Converts a list of tickets into a list of ticket DTOs.
     @param list the list of tickets to convert
     @return a list of ticket DTOs corresponding to the input list
     */
    private List<TicketDTO> parsingTicketInTicketDTO(List<Ticket> list) {
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : list) {
            ticketDTOs.add(new TicketDTO(ticket.getId(), ticket.getPlace(),  ticket.getSession().getFilm().getName(), ticket.getSession().getPrice(), ticket.getSession().getTimestamp(), ticket.getUser()));
        }
        return ticketDTOs;
    }
    /**

     Retrieves a list of ticket DTOs corresponding to a given session ID.
     @param id_session the ID of the session to retrieve tickets for
     @return a list of ticket DTOs corresponding to the session
     */

    public List<TicketDTO> getTickets(int id_session) {
        List<Ticket> tickets = TicketDao.get_tickets(id_session);
        return parsingTicketInTicketDTO(tickets);

    }
}
