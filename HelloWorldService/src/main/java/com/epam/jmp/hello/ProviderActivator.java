package com.epam.jmp.hello;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Tim Ryzhov
 */
public class ProviderActivator implements BundleActivator{

    private ServiceRegistration registration;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Hello World service started in the container");
        registration = bundleContext.registerService(HelloWorldService.class.getName(),
                new HelloWorldServiceImpl(),
                null);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        registration.unregister();
    }
}
