package com.epam.jmp;

import com.epam.jmp.hello.HelloWorldService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * @author Tim Ryzhov
 */
public class HelloWorldActivator implements BundleActivator{

    private HelloWorldConsumer consumer;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference reference = bundleContext.getServiceReference(HelloWorldService.class.getName());
        System.out.println("Consumer version 1.1 is starting");
        consumer = new HelloWorldConsumer((HelloWorldService) bundleContext.getService(reference));
        consumer.startTimer();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        consumer.stopTimer();
    }
}


