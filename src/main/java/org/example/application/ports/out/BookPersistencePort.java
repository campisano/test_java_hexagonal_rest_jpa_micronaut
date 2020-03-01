package org.example.application.ports.out;

import java.util.List;
import java.util.Optional;

import org.example.application.ports.dtos.BookDTO;

public interface BookPersistencePort {

    BookDTO create(BookDTO dto);

    BookDTO updateByIsbn(BookDTO dto);

    List<BookDTO> findAll();

    Optional<BookDTO> findByIsbn(String isbn);

    boolean existBookWithIsbn(String isbn);
}