package org.example.adapters.controllers;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class BookScheme {
    public String isbn;
    public String title;
    public String author;
    public String description;

    public BookScheme(String isbn, String title, String author, String description) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
    }
}