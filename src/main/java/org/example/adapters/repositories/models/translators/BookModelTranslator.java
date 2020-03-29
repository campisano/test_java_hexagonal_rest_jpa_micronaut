package org.example.adapters.repositories.models.translators;

import org.example.adapters.repositories.models.BookModel;
import org.example.application.dtos.BookDTO;

public class BookModelTranslator {

    public static BookModel fromDTO(BookDTO dto) {
        return new BookModel(dto.getIsbn(), dto.getTitle(), dto.getAuthor(), dto.getDescription());
    }

    public static BookDTO toDTO(BookModel model) {
        return new BookDTO(model.getIsbn(), model.getTitle(), model.getAuthor(), model.getDescription());
    }
}
