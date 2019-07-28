package com.zzzkvidi4.logger;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.zzzkvidi4.logger.logger.Logger;
import com.zzzkvidi4.logger.logger.LoggerMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * Main class of application.
 */
public final class Application {
    @NotNull
    private final Map<LoggerMode, Logger> loggers;

    @Inject
    public Application(@NotNull Set<Logger> loggers) {
        this.loggers = loggers.stream().collect(toMap(Logger::getLoggerMode, Function.identity()));
    }

    /**
     * Entry point of application.
     *
     * @param args - command line arguments
     */
    public static void main(@NotNull String[] args) {
        Application application = Guice.createInjector(new MainModule()).getInstance(Application.class);
        application.waitForInput();
    }

    /**
     * Method to configure logger if it is required and input messages.
     */
    public void waitForInput() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Check one of logging types (FILE|CONSOLE|COMPOSITE): ");
            String value;
            do {
                System.out.print("> ");
                value = reader.readLine();
            } while (!StringUtils.hasText(value) || !isConvertable(value));
            Logger logger = loggers.get(LoggerMode.getValue(value));
            if (logger == null) {
                throw new RuntimeException("No logger for such mode!");
            }
            logger.configure();
            System.out.println("Waiting for new lines. Key in Ctrl+C/Ctrl+D to exit.");
            while (true) {
                System.out.print("> ");
                String input = reader.readLine();
                if (input == null) {
                    break;
                }
                logger.log(input);
            }
            logger.tearDown();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to check is it possible to convert string to enum value.
     *
     * @param value - string value
     * @return      - true if string can be transformed into enum value
     */
    private boolean isConvertable(@Nullable String value) {
        return LoggerMode.getValue(value) != null;
    }
}
