package com.epam.jmp;

import com.epam.jmp.servlet.ServerTimeServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.awt.*;
import java.net.URI;

/**
 * @author Tim Ryzhov
 */
public class JettyServer  extends Server {

    public static final int PORT = 8888;
    public static final String SERVERTIME_ENDPOINT = "/servertime";

    public JettyServer(boolean isOpenBrowser) throws Exception {
        super(PORT);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        setHandler(context);

        context.addServlet(new ServletHolder(new ServerTimeServlet()), SERVERTIME_ENDPOINT);
        start();

        if (isOpenBrowser && Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI("http://localhost:" + PORT + SERVERTIME_ENDPOINT));
        }
        System.out.println("Server on port " + PORT + " started successfully");
        join();
    }

    public static void main(String[] args) throws Exception {
        new JettyServer(true);
    }
}
