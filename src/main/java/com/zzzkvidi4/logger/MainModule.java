package com.zzzkvidi4.logger;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.multibindings.Multibinder;
import com.zzzkvidi4.logger.annotation.MessageRetriever;
import com.zzzkvidi4.logger.configurator.CmdConfigurator;
import com.zzzkvidi4.logger.configurator.Configurator;
import com.zzzkvidi4.logger.logger.*;

/**
 * Main module to configure injections.
 */
public final class MainModule extends AbstractModule {
    /**
     * Method to fill context of application with dependencies.
     */
    @Override
    protected void configure() {
        Multibinder<IndexedPrioritizedLogger> indexedLoggers = Multibinder.newSetBinder(binder(), IndexedPrioritizedLogger.class);
        indexedLoggers.addBinding().to(FileLogger.class);
        indexedLoggers.addBinding().to(CmdLogger.class);
        Multibinder<Logger> loggers = Multibinder.newSetBinder(binder(), Logger.class);
        loggers.addBinding().to(FileLogger.class);
        loggers.addBinding().to(CmdLogger.class);
        loggers.addBinding().to(DelegatingLogger.class);
        bind(Configurator.class).to(CmdConfigurator.class);
        bindInterceptor(Matchers.identicalTo(CmdLogger.class), Matchers.annotatedWith(MessageRetriever.class), new LoggerInterceptor());
    }
}
