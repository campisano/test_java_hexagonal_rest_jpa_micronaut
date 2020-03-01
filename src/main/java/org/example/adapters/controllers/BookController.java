package org.example.adapters.controllers;

import java.util.List;
import java.util.Optional;

import org.example.application.ports.dtos.BookDTO;
import org.example.application.ports.in.BookUseCasePort;
import org.example.application.ports.in.IsbnAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

@Controller("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    private BookUseCasePort bookUseCasePort;

    public BookController(BookUseCasePort bookUseCasePort) {
        this.bookUseCasePort = bookUseCasePort;
    }

    @Post("/")
    public HttpResponse<BookDTO> add(HttpRequest<BookDTO> request) {
        LOGGER.info("{}::{} [{}]", request.getUri(), request.getMethod(), request.getBody());

        if (!request.getBody().isPresent()) {
            return HttpResponse.status(HttpStatus.BAD_REQUEST);
        }

        try {
            BookDTO bookDTO = bookUseCasePort.addBook(request.getBody().get());
            LOGGER.info("created, bookDTO={}", bookDTO);
            return HttpResponse.created(bookDTO);
        } catch (IsbnAlreadyExistsException exception) {
            LOGGER.error("exception, message={}", exception.getMessage());
            return HttpResponse.status(HttpStatus.FORBIDDEN);
        }
    }

    @Get("/{isbn}")
    public HttpResponse<BookDTO> getByIsbn(HttpRequest<?> request, String isbn) {
        LOGGER.info("{}::{} [{}]", request.getUri(), request.getMethod(), request.getBody());

        Optional<BookDTO> optDto = bookUseCasePort.findByIsbn(isbn);

        if (!optDto.isPresent()) {
            LOGGER.error("not found, isbn={}", isbn);
            return HttpResponse.notFound();
        }

        LOGGER.error("ok, isbn={}", isbn);
        return HttpResponse.ok(optDto.get());
    }

    @Get("/")
    public HttpResponse<List<BookDTO>> getAll(HttpRequest<?> request) {
        LOGGER.info("{}::{} [{}]", request.getUri(), request.getMethod(), request.getBody());

        return HttpResponse.ok(bookUseCasePort.findAll());
    }
}