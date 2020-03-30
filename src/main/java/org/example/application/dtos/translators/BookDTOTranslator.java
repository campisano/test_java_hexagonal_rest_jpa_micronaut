package org.example.application.dtos.translators;

import org.example.application.dtos.BookDTO;
import org.example.application.exceptions.BookInvalidException;
import org.example.domain.Book;

public class BookDTOTranslator {

    public static Book fromDTO(BookDTO dto) throws BookInvalidException {
        return new Book(dto.getIsbn(), dto.getTitle(), dto.getAuthor(), dto.getDescription());
    }

    public static BookDTO toDTO(Book model) {
        return new BookDTO(model.getIsbn(), model.getTitle(), model.getAuthor(), model.getDescription());
    }
}
