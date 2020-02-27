package org.example.application.dtos;

public class BookDTO {

    private String isbn;
    private String title;
    private String author;
    private String description;

    public BookDTO() {
    }

    public BookDTO(String isbn, String title, String author, String description) {

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BookDTO [isbn=" + isbn + ", title=" + title + ", author=" + author + ", description=" + description
                + "]";
    }
}
