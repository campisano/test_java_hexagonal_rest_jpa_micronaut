package org.example.application.usecases;

import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import org.example.application.ports.dtos.BookDTO;
import org.example.application.ports.dtos.BookDTOTranslator;
import org.example.application.ports.in.BookUseCasePort;
import org.example.application.ports.in.IsbnAlreadyExistsException;
import org.example.application.ports.out.BookPersistencePort;
import org.example.domain.Book;

@Named
public class BookUseCase implements BookUseCasePort {

    private BookPersistencePort bookPersistencePort;

    public BookUseCase(BookPersistencePort bookPersistencePort) {
        this.bookPersistencePort = bookPersistencePort;
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {

        Book book = BookDTOTranslator.fromDTO(bookDTO);

        if (bookPersistencePort.findByIsbn(book.getIsbn()).isPresent()) {
            throw new IsbnAlreadyExistsException(book.getIsbn());
        }

        return bookPersistencePort.create(BookDTOTranslator.toDTO(book));
    }

    @Override
    public List<BookDTO> findAll() {
        return bookPersistencePort.findAll();
    }

    @Override
    public Optional<BookDTO> findByIsbn(String isbn) {
        Optional<BookDTO> book = bookPersistencePort.findByIsbn(isbn);
        if (!book.isPresent()) {
            return Optional.<BookDTO>empty();
        }

        return book;
    }
}
