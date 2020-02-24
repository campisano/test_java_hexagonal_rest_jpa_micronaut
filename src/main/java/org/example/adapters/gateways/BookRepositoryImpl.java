package org.example.adapters.gateways;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.application.domain.entities.Book;
import org.example.application.domain.repositories.BookRepository;

import io.micronaut.data.annotation.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private BookRepositoryJPA bookRepositoryJPA;

    public BookRepositoryImpl(BookRepositoryJPA bookRepositoryJPA) {
        this.bookRepositoryJPA = bookRepositoryJPA;
    }

    public boolean existBookWithTitle(String title) {
        return bookRepositoryJPA.findByTitle(title).isPresent();
    }

    public void save(Book book) {
        bookRepositoryJPA.save(book);
    }

    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        bookRepositoryJPA.findAll().forEach(list::add);
        return list;
    }

    public Optional<Book> findByTitle(String title) {
        return bookRepositoryJPA.findByTitle(title);
    }
}