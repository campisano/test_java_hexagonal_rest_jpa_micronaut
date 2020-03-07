package org.example.application.usecases;

import java.util.List;

import javax.inject.Named;

import org.example.application.ports.dtos.BookDTO;
import org.example.application.ports.in.GetAllBookUseCasePort;
import org.example.application.ports.out.BookPersistencePort;

@Named
public class GetAllBookUseCase implements GetAllBookUseCasePort {

    private BookPersistencePort bookPersistencePort;

    public GetAllBookUseCase(BookPersistencePort bookPersistencePort) {
        this.bookPersistencePort = bookPersistencePort;
    }

    @Override
    public List<BookDTO> execute() {
        return bookPersistencePort.findAll();
    }
}
