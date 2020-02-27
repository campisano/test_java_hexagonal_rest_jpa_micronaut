package org.example.domain.entities;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String description;

    public Book(String isbn, String title, String author, String description) {
        super();
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
}
