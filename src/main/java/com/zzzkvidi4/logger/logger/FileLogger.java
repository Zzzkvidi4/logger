package com.zzzkvidi4.logger.logger;

import com.google.inject.Inject;
import com.zzzkvidi4.logger.configurator.Configurator;
import com.zzzkvidi4.logger.StringUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * File logger.
 */
@RequiredArgsConstructor(onConstructor_ = @Inject)
public final class FileLogger implements IndexedPrioritizedLogger {
    @NotNull
    private static final String LOG_FORMAT = "<%1$s>%2$s: %3$s</%1$s>" + System.lineSeparator();
    @NonNull
    @NotNull
    private final Configurator configurator;
    @Nullable
    @Setter(onParam_ = @NonNull)
    private String fileName;
    @Nullable
    @Setter(onParam_ = @NonNull)
    private String tag;
    private int messageIndex = 0;
    @Nullable
    private Writer writer;

    /**
     * Priority of logger.
     *
     * @return - priority
     */
    @Override
    public int getPriority() {
        return 2;
    }

    /**
     * Method to get message index.
     *
     * @return - message index
     */
    @Override
    public int getMessageIndex() {
        return messageIndex;
    }

    /**
     * Method to configure logger.
     */
    @Override
    public void configure() {
        configurator.setUpParameter("Input file name for logging: ", StringUtils::hasText, this::setFileName);
        configurator.setUpParameter("Input file name for tag: ", StringUtils::hasText, this::setTag);
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to log message.
     *
     * @param message - value to log
     */
    @Override
    public void log(@NotNull String message) {
        log(++messageIndex, message);
    }

    /**
     * Method to log message.
     *
     * @param messageIndex - index of message
     * @param message      - message text
     */
    @Override
    public void log(int messageIndex, @NotNull String message) {
        checkInitialization();
        try {
            writer.write(String.format(LOG_FORMAT, tag, messageIndex, message));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to tear down logger.
     */
    @Override
    public void tearDown() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        return LoggerMode.FILE;
    }

    /**
     * Method to check logger initialization.
     */
    private void checkInitialization() {
        if (fileName == null) {
            throw new IllegalStateException("File name is not initialized!");
        }
        if (tag == null) {
            throw new IllegalStateException("Tag is not initialized!");
        }
        if (writer == null) {
            throw new IllegalStateException("File writer is not initialized!");
        }
    }
}
