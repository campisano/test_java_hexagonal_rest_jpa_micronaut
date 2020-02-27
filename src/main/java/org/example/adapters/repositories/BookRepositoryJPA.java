package org.example.adapters.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.example.adapters.repositories.models.BookModel;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepositoryJPA extends JpaRepository<BookModel, UUID> {

    @Override
    List<BookModel> findAll();

    Optional<BookModel> findByTitle(String title);
}