package org.example.application.ports.in;

import java.util.List;
import java.util.Optional;

import org.example.application.ports.dtos.BookDTO;

public interface BookUseCasePort {

    void addBook(BookDTO bookDTO);

    List<BookDTO> findAll();

    Optional<BookDTO> findByTitle(String title);
}