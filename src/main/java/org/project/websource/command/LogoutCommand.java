package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.project.websource.Path;

import java.io.IOException;

public class LogoutCommand extends Command {
    private static final Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command starts");

        HttpSession session = request.getSession();

            session.removeAttribute("user");
            session.removeAttribute("userRole");

        log.debug("Command finished");
        return Path.PAGE__WELCOME;
    }
}
