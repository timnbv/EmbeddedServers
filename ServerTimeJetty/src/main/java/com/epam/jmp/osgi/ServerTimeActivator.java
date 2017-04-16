package com.epam.jmp.osgi;

import com.epam.jmp.JettyServer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Tim Ryzhov
 */
public class ServerTimeActivator implements BundleActivator {


    private JettyServer server;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        server = new JettyServer(false);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        server.stop();
    }

}
