package com.github.knightliao.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class ConsumerMain {

    public static void main(String[] args) {

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("configuration.xml");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                context.close();
            }
        });

        try {

            while (true) {
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
        }

    }
}
