package org.example.adapters.controllers;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

@Produces
@Singleton
@Requires(classes = { Exception.class, ExceptionHandler.class })
@SuppressWarnings("rawtypes")
public class GlobalExceptionHanlder implements ExceptionHandler<Exception, HttpResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHanlder.class);

    @Override
    public HttpResponse<?> handle(HttpRequest request, Exception exception) {
        LOGGER.error("Request " + request.toString(), exception);
        return HttpResponse.serverError();
    }
}