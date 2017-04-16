package com.epam.jmp;

import com.epam.jmp.hello.HelloWorldService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Tim Ryzhov
 */
public class HelloWorldConsumer implements ActionListener{

    private final HelloWorldService service;
    private final Timer timer;
    private int counter;

    public HelloWorldConsumer(HelloWorldService service) {
        super();

        this.service = service;

        timer = new Timer(1000, this);
    }

    public void startTimer(){
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (counter > 5) {
            timer.stop();
            System.out.println("I'm stopping");
        }
        service.hello();
        counter++;
    }
}
