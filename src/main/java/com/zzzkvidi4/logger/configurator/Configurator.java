package com.zzzkvidi4.logger.configurator;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Interface of configurator.
 */
public interface Configurator {
    /**
     * Method to set up argument.
     *
     * @param prompt    - prompt for user
     * @param validator - method to validate input string
     * @param setter    - method to set value
     */
    void setUpParameter(@NotNull String prompt,
                        @NotNull Predicate<String> validator,
                        @NotNull Consumer<String> setter);
}
