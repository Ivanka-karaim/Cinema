package org.project.websource.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CommandSourceTest {
    @InjectMocks
    CommandSource commandSource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void get() {
        Command result = CommandSource.get("signIn");
        assertEquals(SignInCommand.class, result.getClass());
    }
}