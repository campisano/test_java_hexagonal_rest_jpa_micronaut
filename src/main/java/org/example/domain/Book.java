package org.example.domain;

import java.text.MessageFormat;

import org.example.application.exceptions.BookInvalidException;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String description;

    public Book(String isbn, String title, String author, String description) throws BookInvalidException {
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

    private static void ensureCreable(String isbn, String title, String author, String description)
            throws BookInvalidException {
        if (isbn == null || isbn.length() == 0) {
            throw new BookInvalidException(MessageFormat.format("Isbn {0} is invalid", isbn));
        }

        if (title == null || title.length() == 0) {
            throw new BookInvalidException(MessageFormat.format("Title {0} is invalid", title));
        }

        if (author == null || author.length() == 0) {
            throw new BookInvalidException(MessageFormat.format("Author {0} is invalid", author));
        }
    }
}
