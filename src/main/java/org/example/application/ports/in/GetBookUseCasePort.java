package org.example.application.ports.in;

import org.example.application.ports.dtos.BookDTO;

public interface GetBookUseCasePort {
    BookDTO execute(String isbn) throws IsbnNotExistsException;
}