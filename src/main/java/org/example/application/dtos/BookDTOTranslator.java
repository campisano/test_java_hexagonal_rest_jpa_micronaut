package org.example.application.dtos;

import org.example.domain.Book;

public class BookDTOTranslator {

    public static Book fromDTO(BookDTO dto) {
        return new Book(dto.getIsbn(), dto.getTitle(), dto.getAuthor(), dto.getDescription());
    }

    public static BookDTO toDTO(Book book) {
        return new BookDTO(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getDescription());
    }
}
