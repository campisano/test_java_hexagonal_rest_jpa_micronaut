package org.example.application.usecases;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.example.application.dtos.BookDTO;
import org.example.application.dtos.BookDTOTranslator;
import org.example.application.ports.in.BookUseCasePort;
import org.example.application.ports.out.BookPersistencePort;
import org.example.domain.entities.Book;

public class BookUseCase implements BookUseCasePort {

    @Inject
    private BookPersistencePort bookPersistencePort;

    @Override
    public void addBook(BookDTO bookDTO) {

        Book book = BookDTOTranslator.fromDTO(bookDTO);

        if (bookPersistencePort.findByTitle(book.getTitle()).isPresent()) {
            throw new RuntimeException("Book with this title already exists");
        }

        bookPersistencePort.save(BookDTOTranslator.toDTO(book));
    }

    @Override
    public List<BookDTO> findAll() {
        return bookPersistencePort.findAll();
    }

    @Override
    public Optional<BookDTO> findByTitle(String title) {
        Optional<BookDTO> book = bookPersistencePort.findByTitle(title);
        if (!book.isPresent()) {
            return Optional.<BookDTO>empty();
        }

        return book;
    }
}
