package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.project.dto.TicketDTO;
import org.project.dto.UserDTO;
import org.project.service.TicketService;
import org.project.websource.Path;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountCommandTest {
    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @InjectMocks
    AccountCommand accountCommand;
    UserDTO user;

    @Mock
    HttpSession httpSession;

    @Mock
    TicketService ticketService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new UserDTO(1, "Ivanna", "Karaim", "karaimivanna@gmail.com","user", "123456", "" );

    }

    @Test
    void execute() throws ServletException, IOException, ParseException {
        UserDTO user = new UserDTO();
        user.setId(1);
        List<TicketDTO> tickets = new ArrayList<>();
        tickets.add(new TicketDTO());
        tickets.add(new TicketDTO());

        when(req.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userRole")).thenReturn("user");
        when(httpSession.getAttribute("user")).thenReturn(user);
        when(ticketService.getTicketsByUser(user.getId())).thenReturn(tickets);

        String expectedPage = Path.PAGE__ACCOUNT_USER;

        // Act
        String actualPage = accountCommand.execute(req, resp);

        // Assert
        assertEquals(expectedPage, actualPage);
//        verify(request).setAttribute("tickets", tickets);
//        verify(session, never()).setAttribute(eq("error"), anyString());


    }
}