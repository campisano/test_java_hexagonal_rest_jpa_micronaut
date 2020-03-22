package org.example.adapters.in.controllers.utils;

import java.text.MessageFormat;
import java.util.Objects;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.server.exceptions.ExceptionHandler;

@Produces
@Singleton
@Requires(classes = { Exception.class, ExceptionHandler.class })
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler implements ExceptionHandler<Exception, HttpResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public HttpResponse<?> handle(HttpRequest request, Exception exception) {

        LOGGER.error(MessageFormat.format("Request: {0}, exception: {1}", Objects.toString(request),
                exception.getMessage()));

        return HttpResponse.<JsonError>serverError().body(new JsonError(exception.getMessage()));
    }
}
