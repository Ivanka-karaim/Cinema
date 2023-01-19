package org.project.websource.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.project.dto.UserDTO;
import org.project.websource.Path;
import org.project.service.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class SignInCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private static final UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String errorMessage;
        HttpSession session = request.getSession();
        String forward = Path.PAGE__ERROR_PAGE;
        UserDTO user =new UserDTO(0, request.getParameter("name"),request.getParameter("surname"),  request.getParameter("email"), "user", request.getParameter("password"),request.getParameter("phone_number"));
        try {
            userService.createUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            String error=null;
            if (e.getMessage().equals("emptyData")){
                error = "emptyData";
                System.out.println("emptyData");
            } else if (e.getMessage().equals("errorPhoneNumber")){
                error="errorPhoneNumber";
                System.out.println("errorPhoneNumber");
            }else if (e.getMessage().equals("errorEmail")){
                error = "errorEmail";
                System.out.println("errorEmail");
            } else if(e.getMessage().equals("errorEmailUser")){
                error = "errorEmailUser";
            }
            session.setAttribute("error", error);
            request.setAttribute("error", error);
            return "signIn.jsp";
//            return Path.PAGE__SIGN_IN;
        }

        session.setAttribute("user",user);
        session.setAttribute("userRole", "user");
        session.removeAttribute("error");
        forward=Path.PAGE__ACCOUNT_USER;
        request.setAttribute("email", user.email);
        return request.getRequestURL().toString();
    }
}
