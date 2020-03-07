package org.example.adapters.repositories.book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.example.application.ports.dtos.BookDTO;
import org.example.application.ports.out.BookPersistencePort;

@Singleton
public class BookRepositoryAdapter implements BookPersistencePort {

    private BookRepositoryJPA bookRepositoryJPA;

    public BookRepositoryAdapter(BookRepositoryJPA bookRepositoryJPA) {
        this.bookRepositoryJPA = bookRepositoryJPA;
    }

    @Override
    public BookDTO create(BookDTO dto) {
        BookModel model = BookModelTranslator.fromDTO(dto);

        model = bookRepositoryJPA.save(model);

        return BookModelTranslator.toDTO(model);
    }

    @Override
    @Transactional
    public BookDTO updateByIsbn(BookDTO dto) {
        if (dto.getIsbn() == null) {
            throw new RuntimeException("Isbn cannot be null");
        }

        Optional<BookModel> optModel = bookRepositoryJPA.findByIsbn(dto.getIsbn());
        if (!optModel.isPresent()) {
            throw new RuntimeException("No book exist with specified isbn " + dto.getIsbn());
        }

        BookModel model = optModel.get();
        model.setIsbn(dto.getIsbn());
        model.setTitle(dto.getTitle());
        model.setAuthor(dto.getAuthor());
        model.setDescription(dto.getDescription());

        model = bookRepositoryJPA.save(model);

        return BookModelTranslator.toDTO(model);
    }

    @Override
    public List<BookDTO> findAll() {
        return bookRepositoryJPA.findAll().stream().map(BookModelTranslator::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> findByIsbn(String isbn) {
        Optional<BookModel> optModel = bookRepositoryJPA.findByIsbn(isbn);

        if (!optModel.isPresent()) {
            return Optional.<BookDTO>empty();
        }

        return Optional.of(BookModelTranslator.toDTO(optModel.get()));
    }

    @Override
    public boolean existBookWithIsbn(String isbn) {
        return bookRepositoryJPA.findByIsbn(isbn).isPresent();
    }
}
