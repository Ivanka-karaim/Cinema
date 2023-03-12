package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.project.websource.Path;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EditSessionsCommandTest {
    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;
    @InjectMocks
    EditSessionsCommand editSessionsCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void execute() throws ServletException, IOException {

        String result = editSessionsCommand.execute(req, resp);
        assertEquals(Path.PAGE__LIST_MENU_ADMIN, result);
    }
}