package org.example.application.ports.in;

import java.util.List;

import org.example.application.ports.dtos.BookDTO;

public interface ListAllBookUseCasePort {

    List<BookDTO> execute();
}