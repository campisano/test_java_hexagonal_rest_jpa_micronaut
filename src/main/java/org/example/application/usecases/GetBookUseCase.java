package org.example.application.usecases;

import java.util.Optional;

import javax.inject.Named;

import org.example.application.ports.dtos.BookDTO;
import org.example.application.ports.in.GetBookUseCasePort;
import org.example.application.ports.in.IsbnNotExistsException;
import org.example.application.ports.out.BookPersistencePort;

@Named
public class GetBookUseCase implements GetBookUseCasePort {

    private BookPersistencePort bookPersistencePort;

    public GetBookUseCase(BookPersistencePort bookPersistencePort) {
        this.bookPersistencePort = bookPersistencePort;
    }

    @Override
    public BookDTO execute(String isbn) throws IsbnNotExistsException {
        Optional<BookDTO> book = bookPersistencePort.findByIsbn(isbn);

        if (!book.isPresent()) {
            throw new IsbnNotExistsException(isbn);
        }

        return book.get();
    }
}
