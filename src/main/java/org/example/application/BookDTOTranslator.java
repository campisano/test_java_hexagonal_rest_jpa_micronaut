package org.example.application;

import java.util.ArrayList;
import java.util.List;

import org.example.application.domain.entities.Book;

public class BookDTOTranslator {

    public static Book translateDTO(BookDTO bookDTO) {
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());

        return book;
    }

    public static List<Book> translateDTO(List<BookDTO> bookDTOs) {
        ArrayList<Book> books = new ArrayList<Book>();

        for (BookDTO bookDTO : bookDTOs) {
            books.add(translateDTO(bookDTO));
        }

        return books;
    }

    public static BookDTO translate(Book book) {
        return new BookDTO(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getDescription());
    }

    public static List<BookDTO> translate(List<Book> books) {
        ArrayList<BookDTO> booksDTO = new ArrayList<BookDTO>();

        for (Book book : books) {
            booksDTO.add(translate(book));
        }

        return booksDTO;
    }
}
