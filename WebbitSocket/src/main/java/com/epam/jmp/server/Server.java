package com.epam.jmp.server;

import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * @author Tim Ryzhov
 */
public class Server {

    public WebServer getWebServer() {
        return webServer;
    }

    private final WebServer webServer;

    public Server(final InfoProvider broadcaster) throws IOException, ExecutionException, InterruptedException {

        webServer = WebServers.createWebServer(8888)
                .add(new StaticFileHandler("webbitsocket/src/main/resources/web"))
                .add("/memoryUsage", new EventHandler(broadcaster))
                .add("/hello", new HelloWorldHandler());
        webServer.start().get();
    }

    public static void main(String[] args) throws URISyntaxException, ExecutionException, InterruptedException, IOException {
        ExecutorService webThread = newSingleThreadExecutor();
        final InfoProvider broadcaster = new InfoProvider();
        System.out.println("Server running at " + new Server(broadcaster).getWebServer().getUri());
        broadcaster.pushPeriodicallyOn(webThread);
    }
}
