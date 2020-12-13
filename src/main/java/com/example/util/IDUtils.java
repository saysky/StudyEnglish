package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class IDUtils {
    public static final Logger logger = LoggerFactory.getLogger(IDUtils.class);

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {

        System.out.println(getUUID());
        logger.debug("test");
    }
}