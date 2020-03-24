package org.example.adapters.out.details;

import java.util.List;
import java.util.Optional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

@Repository
public interface BooksModelRepository extends GenericRepository<BookModel, Long> {
    public List<BookModel> findAll();

    public Optional<BookModel> findByIsbn(String isbn);

    public BookModel save(BookModel model);
}