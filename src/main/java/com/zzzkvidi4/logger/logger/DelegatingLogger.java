package com.zzzkvidi4.logger.logger;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.*;

/**
 * Composite logger.
 */
@NonNull
public final class DelegatingLogger implements Logger {
    @NotNull
    private final PriorityQueue<IndexedPrioritizedLogger> loggers =
            new PriorityQueue<>(Comparator.comparingInt(IndexedPrioritizedLogger::getPriority));
    private int messageCount = 0;

    @Inject
    public DelegatingLogger(@NotNull Set<IndexedPrioritizedLogger> loggers) {
        this.loggers.addAll(loggers);
    }

    /**
     * Method to configure loggers.
     */
    @Override
    public void configure() {
        for (IndexedLogger logger: loggers) {
            logger.configure();
        }
    }

    /**
     * Method to log message.
     *
     * @param message - value to log
     */
    @Override
    public void log(@NotNull String message) {
        for (IndexedLogger logger : loggers) {
            logger.log(++messageCount, message);
        }
    }

    /**
     * Method to tear down loggers.
     */
    @Override
    public void tearDown() {
        for (IndexedLogger logger : loggers) {
            logger.tearDown();
        }
    }

    /**
     * Method to get logger mode.
     *
     * @return - logger mode
     */
    @NotNull
    @Override
    public LoggerMode getLoggerMode() {
        return LoggerMode.COMPOSITE;
    }
}
