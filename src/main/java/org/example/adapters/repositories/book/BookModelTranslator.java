package org.example.adapters.repositories.book;

import org.example.application.ports.dtos.BookDTO;

class BookModelTranslator {

    static BookModel fromDTO(BookDTO bookDTO) {
        return new BookModel(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getDescription());
    }

    static BookDTO toDTO(final BookModel bookModel) {
        return new BookDTO(bookModel.getIsbn(), bookModel.getTitle(), bookModel.getAuthor(),
                bookModel.getDescription());
    }
}
