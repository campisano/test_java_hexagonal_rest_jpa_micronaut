package org.example.adapters.controllers;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.example.application.dtos.BookDTO;
import org.example.application.ports.in.BookUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("/books")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Inject
    private BookUseCasePort bookUseCasePort;

    @Post
    public void add(BookDTO bookDTO) {
        LOGGER.info("post, bookDTO={}", bookDTO);
        bookUseCasePort.addBook(bookDTO);
        LOGGER.info("book added");
    }

    @Get("/{title}")
    public Optional<BookDTO> getByTitle(String title) {
        LOGGER.info("get, title={}", title);
        return bookUseCasePort.findByTitle(title);
    }

    @Get
    public List<BookDTO> getAll() {
        LOGGER.info("get");
        return bookUseCasePort.findAll();
    }
}