package org.example.application;

import java.util.List;
import java.util.Optional;

import org.example.application.domain.entities.Book;
import org.example.application.domain.repositories.BookRepository;

public class BookUseCase {

    private BookRepository bookRepository;

    public BookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(BookDTO book) {
        if (bookRepository.findByTitle(book.getTitle()).isPresent()) {
            throw new RuntimeException("Book with this title already exists");
        }
        bookRepository.save(BookDTOTranslator.translateDTO(book));
    }

    public List<BookDTO> findAll() {
        return BookDTOTranslator.translate(bookRepository.findAll());
    }

    public Optional<BookDTO> findByTitle(String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        if (!book.isPresent()) {
            return Optional.<BookDTO>empty();
        }

        return Optional.of(BookDTOTranslator.translate(book.get()));
    }
}
