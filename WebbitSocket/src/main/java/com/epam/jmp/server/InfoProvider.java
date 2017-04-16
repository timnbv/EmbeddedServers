package com.epam.jmp.server;

import com.epam.jmp.MemoryConsumption;
import org.webbitserver.EventSourceConnection;
import org.webbitserver.EventSourceMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static java.lang.Thread.sleep;

/**
 * @author Tim Ryzhov
 */
public class InfoProvider {

    private List<EventSourceConnection> connections = new ArrayList<EventSourceConnection>();
    private int count = 1;

    public void addConnection(EventSourceConnection connection) {
        connection.data("id", count++);
        connections.add(connection);
        broadcast("Client " + connection.data("id") + " joined");
    }

    public void removeConnection(EventSourceConnection connection) {
        connections.remove(connection);
        broadcast("Client " + connection.data("id") + " left");
    }

    public void pushPeriodicallyOn(ExecutorService webThread) throws InterruptedException, ExecutionException {
        while (true) {
            sleep(1000);
            webThread.submit(new Runnable() {
                @Override
                public void run() {
                   broadcast(getData());
                }
            }).get();
        }
    }

    private String getData() {
        Map<String, String> memoryInUse = MemoryConsumption.getMemoryInUse();
        StringBuilder message = new StringBuilder();
        message.append("Connectons count: ").append(connections.size()).append("<br>");
        for (String key : memoryInUse.keySet()) {
            message.append(key).append(":").append(memoryInUse.get(key)).append("<br>");
        }
        return message.toString().replaceAll("\n", "<br>");
    }

    private void broadcast(String message) {
        for (EventSourceConnection connection : connections) {
            connection.send(new EventSourceMessage(message));
        }
    }
}
