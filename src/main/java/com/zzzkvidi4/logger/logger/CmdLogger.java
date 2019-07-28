package com.zzzkvidi4.logger.logger;

import com.zzzkvidi4.logger.annotation.MessageRetriever;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Command line logger.
 */
@NoArgsConstructor
@SuppressWarnings("FinalOrAbstractClass")
public class CmdLogger implements IndexedPrioritizedLogger {
    @NotNull
    private static final String BASIC_LOG_FORMAT = "%s: %s";
    private boolean indexChanged = false;
    private int messageCount = 0;

    /**
     * Method to get priority of logger.
     *
     * @return - logger priority
     */
    @Override
    public int getPriority() {
        return 1;
    }

    /**
     * Method to get message index.
     *
     * @return - index of message for logger.
     */
    @Override
    public int getMessageIndex() {
        return messageCount;
    }

    /**
     * Method to log messages.
     *
     * @param message - value to log
     */
    @Override
    public void log(@NotNull String message) {
        if (!indexChanged) {
            ++messageCount;
            indexChanged = true;
        }
        log(messageCount, message);
        indexChanged = false;
    }

    /**
     * Method to log message.
     *
     * @param messageIndex - index of message
     * @param message      - message text
     */
    @Override
    public void log(int messageIndex, @NotNull String message) {
        if (!indexChanged) {
            ++messageCount;
            indexChanged = true;
        }
        System.out.println(getMessage(messageIndex, message));
        indexChanged = false;
    }

    /**
     * Method to create message.
     *
     * @param index   - index of message
     * @param message - message text
     * @return        - final text message
     */
    @NotNull
    @MessageRetriever
    public String getMessage(int index, @Nullable String message) {
        return String.format(BASIC_LOG_FORMAT, index, message);
    }

    /**
     * Method to get logger mode.
     *
     * @return - logger mode
     */
    @NotNull
    @Override
    public LoggerMode getLoggerMode() {
        return LoggerMode.CONSOLE;
    }
}
