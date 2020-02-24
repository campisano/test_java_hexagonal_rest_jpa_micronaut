package org.example.adapters.gateways;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.example.application.domain.entities.Book;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface BookRepositoryJPA extends CrudRepository<Book, UUID> {

    @Override
    List<Book> findAll();

    Optional<Book> findByTitle(String title);
}