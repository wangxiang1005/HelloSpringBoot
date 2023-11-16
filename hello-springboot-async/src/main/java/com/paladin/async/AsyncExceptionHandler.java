package com.paladin.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(AsyncExceptionHandler.class);
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("Async method has uncaught exception, params:{}" + Arrays.toString(params));
        if (ex instanceof com.paladin.async.AsyncException) {
            com.paladin.async.AsyncException asyncException = (com.paladin.async.AsyncException) ex;
            log.error("asyncException:"  + asyncException.getMessage());
        }
        log.error("Exception :", ex);
    }
}