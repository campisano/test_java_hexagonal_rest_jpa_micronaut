package org.example.application.usecases;

import java.util.Optional;

import org.example.application.dtos.BookDTO;
import org.example.application.exceptions.IsbnNotExistsException;
import org.example.application.ports.MockedBooksRepositoryPort;
import org.example.application.ports.in.GetBookUseCasePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGetBookUseCase {

    static final String sampleIsbn = "sample_isbn";
    static final BookDTO sampleBookDto = new BookDTO(sampleIsbn, "sample_title", "sample_author", "sample_description");

    MockedBooksRepositoryPort booksRepository;
    GetBookUseCasePort getBookUseCase;

    @BeforeEach
    void beforeEach() {
        booksRepository = new MockedBooksRepositoryPort();
        getBookUseCase = new GetBookUseCase(booksRepository);
    }

    @Test
    void when_get_existent_then_call_findByIsbn() throws Exception {
        booksRepository.findByIsbn_out = Optional.of(sampleBookDto);

        getBookUseCase.execute(sampleIsbn);

        Assertions.assertEquals(sampleBookDto.getIsbn(), booksRepository.findByIsbn_in.toString());
    }

    @Test
    void when_get_existent_then_it_returns() throws Exception {
        booksRepository.findByIsbn_out = Optional.of(sampleBookDto);

        BookDTO book = getBookUseCase.execute(sampleIsbn);

        Assertions.assertEquals(sampleBookDto.toString(), book.toString());
    }

    @Test
    void when_get_not_existent_then_throw_exception() throws Exception {
        booksRepository.findByIsbn_out = Optional.empty();

        Assertions.assertThrows(IsbnNotExistsException.class, () -> {
            getBookUseCase.execute(sampleIsbn);
        });
    }
}