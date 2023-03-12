package org.project.websource.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
/**

 A filter that sets the response content type to "text/html;charset=utf-8"

 and the request character encoding to "utf8" to ensure proper encoding of UTF-8 characters.
 */
public class UtfFilter extends HttpServlet implements Filter {
    /**

     Initializes the filter.
     @param arg0 Filter configuration object
     @throws ServletException if there is a problem with the servlet
     */
    public void init(FilterConfig arg0) throws ServletException {
    }
    /**

    Filters the request and response to ensure proper encoding of UTF-8 characters.
    Sets the response content type to "text/html;charset=utf-8" and the request character encoding to "utf8".
    @param request the servlet request
    @param response the servlet response
    @param filterChain the filter chain
    @throws IOException if an I/O error occurs
    */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf8");

        filterChain.doFilter(request, response);

    }

}
