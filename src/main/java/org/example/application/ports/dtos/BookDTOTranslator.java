package org.example.application.ports.dtos;

import java.util.ArrayList;
import java.util.List;

import org.example.domain.entities.Book;

public class BookDTOTranslator {

    public static Book fromDTO(BookDTO bookDTO) {
        Book book = new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getDescription());

        return book;
    }

    public static List<Book> fromDTO(final List<BookDTO> bookDTOs) {
        ArrayList<Book> books = new ArrayList<Book>();

        for (BookDTO bookDTO : bookDTOs) {
            books.add(fromDTO(bookDTO));
        }

        return books;
    }

    public static BookDTO toDTO(final Book book) {
        return new BookDTO(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getDescription());
    }

    public static List<BookDTO> toDTO(final List<Book> books) {
        ArrayList<BookDTO> booksDTO = new ArrayList<BookDTO>();

        for (Book book : books) {
            booksDTO.add(toDTO(book));
        }

        return booksDTO;
    }
}
