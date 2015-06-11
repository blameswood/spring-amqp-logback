package com.github.knightliao.consumer.service;

import org.springframework.amqp.core.Message;

/**
 *
 */
public class ServerHandler {

    public void handleMessage(Message message) {

        System.out.println(message.toString());
    }
}