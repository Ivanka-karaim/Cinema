package org.project.websource.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {

            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            final HttpServletResponse response = (HttpServletResponse) servletResponse;

            final HttpSession session = request.getSession();

            if (session != null && session.getAttribute("user") != null && session.getAttribute("userRole") != null) {

                    filterChain.doFilter(servletRequest, response);
                    return;

            }

            response.getWriter().write(notifyAccessDenied());
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    private String notifyAccessDenied() {
        return "<script>" + "alert('You should sign In!');" + "window.location = 'http://localhost:8080/publicationView?command=publication';" + "</script>";
    }
}
