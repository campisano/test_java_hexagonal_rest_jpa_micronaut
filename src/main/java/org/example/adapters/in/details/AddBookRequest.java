package org.example.adapters.in.details;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class AddBookRequest {
    public String isbn;
    public String title;
    public String author;
    public String description;

    public AddBookRequest(String isbn, String title, String author, String description) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
    }
}