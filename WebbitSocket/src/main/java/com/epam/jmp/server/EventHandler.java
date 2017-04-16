package com.epam.jmp.server;

import org.webbitserver.EventSourceConnection;
import org.webbitserver.EventSourceHandler;

/**
 * @author Tim Ryzhov
 */
public  class EventHandler implements EventSourceHandler {
    private InfoProvider broadcaster;

    public EventHandler(InfoProvider broadcaster) {
        this.broadcaster = broadcaster;
    }


    @Override
    public void onOpen(EventSourceConnection connection) throws Exception {
        broadcaster.addConnection(connection);
    }

    @Override
    public void onClose(EventSourceConnection connection) throws Exception {
        broadcaster.removeConnection(connection);
    }
}
