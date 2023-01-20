package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.websource.Path;

import java.io.IOException;
import java.text.ParseException;

public class ErrorCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        request.setAttribute("error_message",  request.getSession().getAttribute("error"));
        request.getSession().removeAttribute("error");
        return Path.PAGE__ERROR_PAGE;
    }
}
