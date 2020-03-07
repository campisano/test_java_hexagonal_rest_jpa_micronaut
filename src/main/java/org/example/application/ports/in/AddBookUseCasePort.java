package org.example.application.ports.in;

import org.example.application.ports.dtos.BookDTO;

public interface AddBookUseCasePort {

    BookDTO execute(BookDTO bookDTO) throws IsbnAlreadyExistsException;
}