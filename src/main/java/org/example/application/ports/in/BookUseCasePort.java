package org.example.application.ports.in;

import java.util.List;
import java.util.Optional;

import org.example.application.ports.dtos.BookDTO;

public interface BookUseCasePort {

    BookDTO addBook(BookDTO dto) throws IsbnAlreadyExistsException;

    List<BookDTO> findAll();

    Optional<BookDTO> findByIsbn(String isbn);
}