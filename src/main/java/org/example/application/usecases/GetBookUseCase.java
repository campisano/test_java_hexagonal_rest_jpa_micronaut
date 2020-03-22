package org.example.application.usecases;

import java.util.Optional;

import org.example.application.ports.dtos.BookDTO;
import org.example.application.ports.in.GetBookUseCasePort;
import org.example.application.ports.in.IsbnNotExistsException;
import org.example.application.ports.out.BookRepositoryPort;

public class GetBookUseCase implements GetBookUseCasePort {

    private BookRepositoryPort bookRepository;

    public GetBookUseCase(BookRepositoryPort bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO execute(String isbn) throws IsbnNotExistsException {
        Optional<BookDTO> book = bookRepository.findByIsbn(isbn);

        if (!book.isPresent()) {
            throw new IsbnNotExistsException(isbn);
        }

        return book.get();
    }
}
