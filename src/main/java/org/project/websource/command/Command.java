package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public abstract class Command implements Serializable {

        /**
         * Execution method for command.
         * @return Address to go once the command is executed.
         */
        public abstract String execute(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException;

        @Override
        public final String toString() {
            return getClass().getSimpleName();
        }
    }


