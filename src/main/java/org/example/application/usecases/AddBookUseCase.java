package org.example.application.usecases;

import javax.inject.Named;

import org.example.application.ports.dtos.BookDTO;
import org.example.application.ports.dtos.BookDTOTranslator;
import org.example.application.ports.in.AddBookUseCasePort;
import org.example.application.ports.in.IsbnAlreadyExistsException;
import org.example.application.ports.out.BookPersistencePort;
import org.example.domain.Book;

@Named
public class AddBookUseCase implements AddBookUseCasePort {

    private BookPersistencePort bookPersistencePort;

    public AddBookUseCase(BookPersistencePort bookPersistencePort) {
        this.bookPersistencePort = bookPersistencePort;
    }

    @Override
    public BookDTO execute(BookDTO bookDTO) throws IsbnAlreadyExistsException {

        Book book = BookDTOTranslator.fromDTO(bookDTO);

        if (bookPersistencePort.findByIsbn(book.getIsbn()).isPresent()) {
            throw new IsbnAlreadyExistsException(book.getIsbn());
        }

        return bookPersistencePort.create(BookDTOTranslator.toDTO(book));
    }
}
