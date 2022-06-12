package org.example.adapters.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.example.adapters.repositories.models.BookModel;
import org.example.adapters.repositories.models.translators.BookModelTranslator;
import org.example.application.dtos.BookDTO;
import org.example.application.ports.out.BooksRepositoryPort;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

@Singleton
public class JPABooksRepository implements BooksRepositoryPort {

    private MicronautBooksRepository booksRepository;
    private MicronautAuthorsRepository authorsRepository;

    public JPABooksRepository(MicronautBooksRepository booksRepository, MicronautAuthorsRepository authorsRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
    }

    @Transactional
    @Override
    public BookDTO create(BookDTO dto) {
        var authors = authorsRepository.findByNameIn(dto.getAuthors());
        var model = BookModelTranslator.fromDTO(dto, authors);

        model = booksRepository.save(model);

        return BookModelTranslator.toDTO(model);
    }

    @Override
    public List<BookDTO> findAll() {
        return booksRepository.findAll().stream().map(book -> BookModelTranslator.toDTO(book))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> findByIsbn(String isbn) {
        var optModel = booksRepository.findByIsbn(isbn);

        if (!optModel.isPresent()) {
            return Optional.<BookDTO>empty();
        }

        return Optional.of(BookModelTranslator.toDTO(optModel.get()));
    }
}

@Repository
interface MicronautBooksRepository extends GenericRepository<BookModel, Long> {
    public List<BookModel> findAll();

    public Optional<BookModel> findByIsbn(String isbn);

    public BookModel save(BookModel model);
}
