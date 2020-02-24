package org.example.adapters.controllers;

import java.util.List;
import java.util.Optional;

import org.example.application.BookDTO;
import org.example.application.BookUseCase;
import org.example.application.domain.repositories.BookRepository;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("/books")
public class BookController {
    private BookUseCase bookUseCase;

    public BookController(BookRepository bookRepository) {
        this.bookUseCase = new BookUseCase(bookRepository);
    }

    @Post
    public void add(BookDTO book) {
        bookUseCase.addBook(book);
    }

    @Get("/{title}")
    public Optional<BookDTO> getByTitle(String title) {
        return bookUseCase.findByTitle(title);
    }

    @Get
    public List<BookDTO> getAll() {
        return bookUseCase.findAll();
    }
}