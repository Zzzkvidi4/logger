package com.zzzkvidi4.logger.configurator;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Command line configurator.
 */
public final class CmdConfigurator implements Configurator {
    /**
     * Method to set up argument from string.
     *
     * @param prompt    - prompt for user
     * @param validator - method to validate input string
     * @param setter    - method to set value
     */
    @Override
    public void setUpParameter(@NotNull String prompt, @NotNull Predicate<String> validator, @NotNull Consumer<String> setter) {
        System.out.println(prompt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String value;
            do {
                System.out.print("> ");
                value = reader.readLine();
            } while (!validator.test(value));
            setter.accept(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
