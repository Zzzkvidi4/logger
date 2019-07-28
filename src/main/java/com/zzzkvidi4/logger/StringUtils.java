package com.zzzkvidi4.logger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

/**
 * Class to store utils to work with strings.
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public final class StringUtils {
    /**
     * Method to check string on text containing.
     *
     * @param value - string value
     * @return      - true if string not null and contains some text after trimming
     */
    public static boolean hasText(@Nullable String value) {
        return value != null && !value.isEmpty() && !value.trim().isEmpty();
    }
}
