package com.epam.jmp;

import com.epam.jmp.servlet.ServerTimeServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author Tim Ryzhov
 */
public class JettyServer extends Server {

    public static final int PORT = 8888;
    public static final String SERVERTIME_ENDPOINT = "servertime";

    public JettyServer(boolean isOpenBrowser) throws Exception {
        super(PORT);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        setHandler(context);

        context.addServlet(new ServletHolder(new ServerTimeServlet()), "/" + SERVERTIME_ENDPOINT);
        start();

        System.out.println("Server on port " + getURI() + SERVERTIME_ENDPOINT);
        join();
    }


}
