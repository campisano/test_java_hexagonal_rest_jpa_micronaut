package org.example.adapters.out.repositories.book;

import org.example.application.dtos.BookDTO;

class BookModelTranslator {

    static BookModel fromDTO(BookDTO dto) {
        return new BookModel(dto.getIsbn(), dto.getTitle(), dto.getAuthor(), dto.getDescription());
    }

    static BookDTO toDTO(BookModel model) {
        return new BookDTO(model.getIsbn(), model.getTitle(), model.getAuthor(), model.getDescription());
    }
}
