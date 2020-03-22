package org.example.application.usecases;

import java.util.List;

import org.example.application.dtos.BookDTO;
import org.example.application.ports.in.ListAllBookUseCasePort;
import org.example.application.ports.out.BooksRepositoryPort;

public class ListAllBookUseCase implements ListAllBookUseCasePort {

    private BooksRepositoryPort booksRepository;

    public ListAllBookUseCase(BooksRepositoryPort booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public List<BookDTO> execute() {
        return booksRepository.findAll();
    }
}
