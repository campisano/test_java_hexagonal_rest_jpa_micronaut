package org.example.application.usecases;

import org.example.application.ports.dtos.BookDTO;
import org.example.application.ports.dtos.BookDTOTranslator;
import org.example.application.ports.in.AddBookUseCasePort;
import org.example.application.ports.in.IsbnAlreadyExistsException;
import org.example.application.ports.out.BookRepositoryPort;
import org.example.domain.Book;

public class AddBookUseCase implements AddBookUseCasePort {

    private BookRepositoryPort bookRepository;

    public AddBookUseCase(BookRepositoryPort bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO execute(BookDTO bookData) throws IsbnAlreadyExistsException {

        Book book = BookDTOTranslator.fromDTO(bookData);

        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new IsbnAlreadyExistsException(book.getIsbn());
        }

        return bookRepository.create(BookDTOTranslator.toDTO(book));
    }
}
