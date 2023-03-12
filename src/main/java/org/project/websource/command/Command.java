package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
/**

 Abstract class that represents a command to be executed in the web application.

 Contains an abstract execute method that takes a request and response as parameters and returns an address to go to after execution.

 Also overrides the toString method to return the simple name of the class.
 */
public abstract class Command implements Serializable {

        /**
         * Execution method for command.
         * @return Address to go once the command is executed.
         */
        public abstract String execute(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException, ParseException;

        @Override
        public final String toString() {
            return getClass().getSimpleName();
        }
    }


