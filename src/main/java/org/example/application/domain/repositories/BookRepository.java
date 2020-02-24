package org.example.application.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.example.application.domain.entities.Book;

public interface BookRepository {
    boolean existBookWithTitle(String title);

    void save(Book book);

    List<Book> findAll();

    Optional<Book> findByTitle(String title);
}
