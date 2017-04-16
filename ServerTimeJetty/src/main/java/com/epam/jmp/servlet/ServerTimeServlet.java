package com.epam.jmp.servlet;

import com.epam.jmp.ServerTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tim Ryzhov
 */
public class ServerTimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("<h1> Server Time: "+ ServerTime.getLocalTime() +"</h1>");
    }
}
