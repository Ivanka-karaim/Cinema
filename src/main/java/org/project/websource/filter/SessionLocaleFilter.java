package org.project.websource.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**

 This filter sets the session locale based on a parameter passed in the request.

 If the "sessionLocale" parameter is present in the request, its value is used to set the session attribute "lang".

 The filter then calls the next filter in the chain by invoking its doFilter() method.
 */
    public class SessionLocaleFilter implements Filter {
    /**

     This method sets the session locale based on a parameter passed in the request.

     If the "sessionLocale" parameter is present in the request, its value is used to set the session attribute "lang".

     The filter then calls the next filter in the chain by invoking its doFilter() method.

     @param request the servlet request

     @param response the servlet response

     @param chain the filter chain

     @throws IOException if an I/O error occurs while processing the request

     @throws ServletException if a servlet error occurs while processing the request
     */
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {

            HttpServletRequest req = (HttpServletRequest) request;

            if (req.getParameter("sessionLocale") != null) {
                req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
            }
            chain.doFilter(request, response);
        }

        public void destroy() {}
        public void init(FilterConfig arg0) throws ServletException {}
    }

