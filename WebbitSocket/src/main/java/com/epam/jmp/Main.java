package com.epam.jmp;

import com.epam.jmp.server.Server;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

/**
 * @author Tim Ryzhov
 */
public class Main {

    public static void main(String[] args) throws URISyntaxException, ExecutionException, InterruptedException, IOException {
        Server server = new Server();
    }

}
