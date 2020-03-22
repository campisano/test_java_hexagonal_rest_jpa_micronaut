package org.example.adapters.out.repositories.book;

import java.util.List;
import java.util.Optional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

@Repository
interface GenericBooksRepository extends GenericRepository<BookModel, Long> {

    List<BookModel> findAll();

    Optional<BookModel> findByIsbn(String isbn);

    BookModel save(BookModel model);
}