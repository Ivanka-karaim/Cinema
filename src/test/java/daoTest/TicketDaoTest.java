package daoTest;

import org.junit.Test;
import org.project.db.dao.TicketDao;
import org.project.db.entity.Ticket;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Objects;

public class TicketDaoTest {
    @Test
    public void testTicketDao(){
        List<Ticket> tickets = TicketDao.getTicketByUser(1);
        assertEquals(1, tickets.get(2).getUser().getId());

        List<Ticket> tickets1 = TicketDao.getTicketByUser(100);
        assertEquals(0, tickets1.size());

        int id_ticket = TicketDao.get_tickets(8).get(20).getId();
        TicketDao.updateTicket(id_ticket, 1);
        assertEquals(1, Objects.requireNonNull(TicketDao.getTicketById(id_ticket)).getUser().getId());


    }
}
