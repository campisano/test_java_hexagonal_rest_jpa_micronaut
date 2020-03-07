package org.example.domain;

import java.util.Objects;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String description;

    public Book(String isbn, String title, String author, String description) {
        ensureCreable(isbn, title, author, description);
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    private static void ensureCreable(String isbn, String title, String author, String description) {
        Objects.requireNonNull(isbn, "Isbn cannot be null");
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(author, "Author cannot be null");
    }
}
