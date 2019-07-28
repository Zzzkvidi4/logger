package com.zzzkvidi4.logger.logger;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.TreeMap;

/**
 * Enum for logger mode.
 */
public enum LoggerMode {
    FILE,      //logging to file
    CONSOLE,   //logging to console
    COMPOSITE; //logging to console and file
    @NotNull
    static final Map<String, LoggerMode> CACHE = new TreeMap<>(String::compareToIgnoreCase);

    static {
        for (LoggerMode mode : values()) {
            CACHE.put(mode.name(), mode);
        }
    }

    /**
     * Method to get enum value from string.
     *
     * @param value - string value
     * @return      - enum value
     */
    @Nullable
    public static LoggerMode getValue(@Nullable String value) {
        return CACHE.get(value);
    }
}
