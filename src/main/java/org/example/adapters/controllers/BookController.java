package org.example.adapters.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.application.BookDTO;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("/books/")
public class BookController {

    List<BookDTO> books = new ArrayList<>();

    @Post
    public BookDTO add(BookDTO book) {
        books.add(book);
        return book;
    }

    @Get("/{title}")
    public Optional<BookDTO> getByTitle(String title) {
        return books.stream().filter(it -> it.getTitle().equals(title)).findFirst();
    }

    @Get
    public List<BookDTO> getAll() {
        return books;

    }
}