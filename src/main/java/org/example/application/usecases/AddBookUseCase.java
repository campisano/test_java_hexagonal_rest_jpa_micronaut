package org.example.application.usecases;

import org.example.application.ports.dtos.BookDTO;
import org.example.application.ports.dtos.BookDTOTranslator;
import org.example.application.ports.in.AddBookUseCasePort;
import org.example.application.ports.in.IsbnAlreadyExistsException;
import org.example.application.ports.out.BooksRepositoryPort;
import org.example.domain.Book;

public class AddBookUseCase implements AddBookUseCasePort {

    private BooksRepositoryPort booksRepository;

    public AddBookUseCase(BooksRepositoryPort booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public BookDTO execute(BookDTO bookData) throws IsbnAlreadyExistsException {

        Book book = BookDTOTranslator.fromDTO(bookData);

        if (booksRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new IsbnAlreadyExistsException(book.getIsbn());
        }

        return booksRepository.create(BookDTOTranslator.toDTO(book));
    }
}
