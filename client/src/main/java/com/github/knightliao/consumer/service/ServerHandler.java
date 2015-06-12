package com.github.knightliao.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class ServerHandler {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public void handleMessage(String message) {

        LOGGER.info(message);
    }
}