package org.example.application.ports.out;

import java.util.List;
import java.util.Optional;

import org.example.application.dtos.BookDTO;

public interface BookPersistencePort {

    boolean existBookWithTitle(String title);

    void save(BookDTO bookDTO);

    List<BookDTO> findAll();

    Optional<BookDTO> findByTitle(String title);
}