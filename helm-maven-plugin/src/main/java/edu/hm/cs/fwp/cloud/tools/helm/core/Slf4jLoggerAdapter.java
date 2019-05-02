package edu.hm.cs.fwp.cloud.tools.helm.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class Slf4jLoggerAdapter implements Consumer<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jLoggerAdapter.class);

    @Override
    public void accept(String s) {
        LOGGER.info(s);
    }
}
