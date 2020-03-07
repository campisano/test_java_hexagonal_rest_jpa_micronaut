package org.example.application.ports.dtos;

import org.example.domain.Book;

public class BookDTOTranslator {

    public static Book fromDTO(BookDTO bookDTO) {
        return new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getDescription());
    }

    public static BookDTO toDTO(final Book book) {
        return new BookDTO(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getDescription());
    }
}
