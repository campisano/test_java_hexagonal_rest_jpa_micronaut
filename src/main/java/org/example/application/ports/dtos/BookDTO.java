package org.example.application.ports.dtos;

public class BookDTO {

    private String isbn;
    private String title;
    private String author;
    private String description;

    public BookDTO(String isbn, String title, String author, String description) {
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

    @Override
    public String toString() {
        return "BookDTO [isbn=" + isbn + ", title=" + title + ", author=" + author + ", description=" + description
                + "]";
    }
}
