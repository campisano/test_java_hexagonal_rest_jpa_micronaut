package org.example.application.usecases;

import java.util.Optional;

import org.example.application.dtos.BookDTO;
import org.example.application.exceptions.IsbnAlreadyExistsException;
import org.example.application.ports.MockedBooksRepositoryPort;
import org.example.application.ports.in.AddBookUseCasePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAddBookUseCase {

    static final BookDTO sampleBookDto = new BookDTO("sample_isbn", "sample_title", "sample_author",
            "sample_description");

    MockedBooksRepositoryPort booksRepository;
    AddBookUseCasePort addBookUseCase;

    @BeforeEach
    void beforeEach() {
        booksRepository = new MockedBooksRepositoryPort();
        addBookUseCase = new AddBookUseCase(booksRepository);
    }

    @Test
    void when_add_new_then_call_findByIsbn() throws Exception {
        booksRepository.findByIsbn_out = Optional.empty();

        addBookUseCase.execute(sampleBookDto);

        Assertions.assertEquals(sampleBookDto.getIsbn(), booksRepository.findByIsbn_in.toString());
    }

    @Test
    void when_add_new_then_call_create() throws Exception {
        booksRepository.create_out = sampleBookDto;
        booksRepository.findByIsbn_out = Optional.empty();

        addBookUseCase.execute(sampleBookDto);

        Assertions.assertEquals(sampleBookDto.toString(), booksRepository.create_in.toString());
    }

    @Test
    void when_add_new_then_new_returns() throws Exception {
        booksRepository.create_out = sampleBookDto;
        booksRepository.findByIsbn_out = Optional.empty();

        BookDTO book = addBookUseCase.execute(sampleBookDto);

        Assertions.assertEquals(sampleBookDto.toString(), book.toString());
    }

    @Test
    void when_add_existent_then_throw_exception() throws Exception {
        booksRepository.findByIsbn_out = Optional.of(sampleBookDto);

        Assertions.assertThrows(IsbnAlreadyExistsException.class, () -> {
            addBookUseCase.execute(sampleBookDto);
        });
    }
}