package org.project.websource;

import jakarta.servlet.jsp.tagext.*;
import jakarta.servlet.jsp.*;

import java.io.*;

public class TextTag extends SimpleTagSupport {
    private String message;
    StringWriter sw = new StringWriter();
    public void doTag() throws JspException, IOException {
        if (message != null) {
            /* Use message from attribute */
            JspWriter out = getJspContext().getOut();
            out.println( message );
        } else {
            /* use message from the body */
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }
    }
    public void setMessage(String msg) {
        this.message = msg;
    }
}
