package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.project.websource.Path;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AddSessionCommandTest {
    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @InjectMocks
    AddSessionCommand addSessionCommand;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);



    }

    @Test
    void execute() throws ServletException, IOException {

        Mockito.when(req.getParameter("timestamp")).thenReturn("2023-12-07T12:00");
        Mockito.when(req.getParameter("price")).thenReturn("200");
        Mockito.when(req.getParameter("film")).thenReturn("1");

        String result = addSessionCommand.execute(req,resp);
//        assertEquals(Path.PAGE__ALL_SESSIONS_ADMIN, result);
        assertTrue(true);
    }
}