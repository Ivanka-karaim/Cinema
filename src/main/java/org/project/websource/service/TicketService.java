package org.project.websource.service;

import org.project.db.dao.TicketDao;
import org.project.db.entity.Session;
import org.project.db.entity.Ticket;

import java.util.List;

public class TicketService {
    public static int countFreePlace(Session session){
        int i=0;
        List<Ticket> tickets = TicketDao.get_tickets(session);
        for (Ticket ticket: tickets){
            if (ticket.getUser()!=null){
                i++;
            }
        }
        return i;
    }
}
