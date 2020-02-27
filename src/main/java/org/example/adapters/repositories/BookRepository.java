package org.example.adapters.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.example.adapters.repositories.models.BookModel;
import org.example.adapters.repositories.models.BookModelTranslator;
import org.example.application.dtos.BookDTO;
import org.example.application.ports.out.BookPersistencePort;

public class BookRepository implements BookPersistencePort {

    @Inject
    private BookRepositoryJPA bookRepositoryJPA;

    @Override
    public boolean existBookWithTitle(String title) {
        return bookRepositoryJPA.findByTitle(title).isPresent();
    }

    @Override
    public void save(BookDTO bookDTO) {
        bookRepositoryJPA.save(BookModelTranslator.fromDTO(bookDTO));

    }

    @Override
    public List<BookDTO> findAll() {
        List<BookModel> bookModels = new ArrayList<>();
        bookRepositoryJPA.findAll().forEach(bookModels::add);
        return BookModelTranslator.toDTO(bookModels);
    }

    @Override
    public Optional<BookDTO> findByTitle(String title) {
        Optional<BookModel> bookModel = bookRepositoryJPA.findByTitle(title);

        if (!bookModel.isPresent()) {
            return Optional.<BookDTO>empty();
        }

        return Optional.of(BookModelTranslator.toDTO(bookModel.get()));
    }
}