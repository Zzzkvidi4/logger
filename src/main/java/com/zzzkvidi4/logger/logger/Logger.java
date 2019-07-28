package com.zzzkvidi4.logger.logger;

import org.jetbrains.annotations.NotNull;

/**
 * Interface of logger.
 */
public interface Logger {
    /**
     * Method to configure logger. Should be called before logging.
     */
    default void configure() {}

    /**
     * Method to log messages.
     *
     * @param message - value to log
     */
    void log(@NotNull String message);

    /**
     * Method to get logger mode for which it is created.
     *
     * @return - logger mode.
     */
    @NotNull LoggerMode getLoggerMode();

    /**
     * Method to tear down logger.
     */
    default void tearDown() {}
}
