package com.zzzkvidi4.logger.logger;

import org.jetbrains.annotations.NotNull;

/**
 * Interface of logger with message index.
 */
public interface IndexedLogger extends Logger {
    /**
     * Method to get message index.
     *
     * @return - index of message
     */
    int getMessageIndex();

    /**
     * Method to log message.
     *
     * @param messageIndex - message index
     * @param message      - message text
     */
    void log(int messageIndex, @NotNull String message);
}
