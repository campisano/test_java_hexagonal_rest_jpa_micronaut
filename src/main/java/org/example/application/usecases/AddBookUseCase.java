package org.example.application.usecases;

import org.example.application.dtos.BookDTO;
import org.example.application.dtos.translators.BookDTOTranslator;
import org.example.application.exceptions.BookInvalidException;
import org.example.application.exceptions.IsbnAlreadyExistsException;
import org.example.application.ports.in.AddBookUseCasePort;
import org.example.application.ports.out.BooksRepositoryPort;
import org.example.domain.Book;

public class AddBookUseCase implements AddBookUseCasePort {

    private BooksRepositoryPort booksRepository;

    public AddBookUseCase(BooksRepositoryPort booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public BookDTO execute(BookDTO bookDto) throws IsbnAlreadyExistsException, BookInvalidException {

        Book book = BookDTOTranslator.fromDTO(bookDto);

        if (booksRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new IsbnAlreadyExistsException(book.getIsbn());
        }

        return booksRepository.create(BookDTOTranslator.toDTO(book));
    }
}
