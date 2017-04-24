package com.epam.jmp.server;

import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.EmbeddedResourceHandler;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * @author Tim Ryzhov
 */
public class Server {

    public Server() throws IOException, ExecutionException, InterruptedException {
        final InfoProvider broadcaster = new InfoProvider();
        WebServer webServer = WebServers.createWebServer(8888)
                .add(new EmbeddedResourceHandler("web"))
                .add("/memoryUsage", new EventHandler(broadcaster))
                .add("/hello", new HelloWorldHandler());
        webServer.start().get();
        System.out.println("Server running at " + webServer.getUri());
        ExecutorService webThread = newSingleThreadExecutor();
        broadcaster.pushPeriodicallyOn(webThread);
    }
}
