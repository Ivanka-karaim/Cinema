package org.project.websource;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.project.websource.command.Command;
import org.project.websource.command.CommandSource;

import java.io.IOException;
import java.text.ParseException;

public class FrontController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter out =  resp.getWriter();
//        out.println("I am FrontController");
//
//    }
    private static final Logger log = Logger.getLogger(FrontController.class);

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String forward = process(request, response);
        RequestDispatcher disp = request.getRequestDispatcher(forward);
        System.out.println("get");
        disp.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String forward = process(request, response);
        System.out.println("post");

        response.sendRedirect(forward);
    }


    private String process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        log.debug("Controller starts");

        String commandName = "";
        // extract command name from the request
        if (request.getParameter("command") == null) {
            commandName = request.getRequestURL().toString().split("/")[4];
        } else {
            commandName = request.getParameter("command");
        }
        System.out.println(commandName);
//        System.out.println(commandName);
        log.trace("Request parameter: command --> " + commandName);

        
        // obtain command object by its name
        Command command = CommandSource.get(commandName);
        log.trace("Obtained command --> " + command);

        // execute command and get forward address
        String forward = null;
        try {
            forward = command.execute(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.trace("Forward address --> " + forward);

        log.debug("Controller finished, now go to forward address --> " + forward);

        if (forward != null) {
            return forward;
        }
        return null;
    }
}

