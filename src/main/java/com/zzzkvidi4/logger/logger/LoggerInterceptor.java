package com.zzzkvidi4.logger.logger;

import com.zzzkvidi4.logger.logger.IndexedLogger;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;

/**
 * Logger interceptor to exchange output result.
 */
public final class LoggerInterceptor implements MethodInterceptor {
    /**
     * Method to add prefix for message creation method.
     *
     * @param invocation - method invocation
     * @return           - message with prefix
     * @throws Throwable - some exception during method call
     */
    @NotNull
    @Override
    public Object invoke(@NotNull MethodInvocation invocation) throws Throwable {
        Object result = invocation.proceed();
        Object methodTarget = invocation.getThis();
        if (!(methodTarget instanceof IndexedLogger)) {
            throw new IllegalArgumentException("Incorrect object!");
        }
        if (!(result instanceof String)) {
            throw new IllegalArgumentException("Interceptor used for incorrect method!");
        }
        IndexedLogger indexedLogger = (IndexedLogger) methodTarget;
        String value = (String) result;
        if (indexedLogger.getMessageIndex() % 3 == 0) {
            return "3-fold input: " + value;
        } else {
            return value;
        }
    }
}
