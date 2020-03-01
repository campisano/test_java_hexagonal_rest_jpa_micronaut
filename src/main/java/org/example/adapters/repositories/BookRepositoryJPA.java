package org.example.adapters.repositories;

import java.util.List;
import java.util.Optional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepositoryJPA extends JpaRepository<BookModel, Long> {

    @Override
    List<BookModel> findAll();

    Optional<BookModel> findByIsbn(String isbn);
}