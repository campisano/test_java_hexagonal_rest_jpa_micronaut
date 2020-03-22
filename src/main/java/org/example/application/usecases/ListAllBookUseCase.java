package org.example.application.usecases;

import java.util.List;

import org.example.application.ports.dtos.BookDTO;
import org.example.application.ports.in.ListAllBookUseCasePort;
import org.example.application.ports.out.BookRepositoryPort;

public class ListAllBookUseCase implements ListAllBookUseCasePort {

    private BookRepositoryPort bookRepository;

    public ListAllBookUseCase(BookRepositoryPort bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> execute() {
        return bookRepository.findAll();
    }
}
