package org.example.adapters.repositories.models;

import java.util.ArrayList;
import java.util.List;

import org.example.application.dtos.BookDTO;

public class BookModelTranslator {

    public static BookModel fromDTO(BookDTO bookDTO) {
        BookModel bookModel = new BookModel();
        bookModel.setIsbn(bookDTO.getIsbn());
        bookModel.setTitle(bookDTO.getTitle());
        bookModel.setAuthor(bookDTO.getAuthor());
        bookModel.setDescription(bookDTO.getDescription());

        return bookModel;
    }

    public static List<BookModel> fromDTO(final List<BookDTO> bookDTOs) {
        ArrayList<BookModel> bookModels = new ArrayList<BookModel>();

        for (BookDTO bookDTO : bookDTOs) {
            bookModels.add(fromDTO(bookDTO));
        }

        return bookModels;
    }

    public static BookDTO toDTO(final BookModel bookModel) {
        return new BookDTO(bookModel.getIsbn(), bookModel.getTitle(), bookModel.getAuthor(),
                bookModel.getDescription());
    }

    public static List<BookDTO> toDTO(final List<BookModel> bookModels) {
        ArrayList<BookDTO> booksDTO = new ArrayList<BookDTO>();

        for (BookModel bookModel : bookModels) {
            booksDTO.add(toDTO(bookModel));
        }

        return booksDTO;
    }
}
