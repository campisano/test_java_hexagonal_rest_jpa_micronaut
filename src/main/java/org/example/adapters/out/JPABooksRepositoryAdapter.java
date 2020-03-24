package org.example.adapters.out;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.example.adapters.out.details.BookModel;
import org.example.adapters.out.details.BookModelTranslator;
import org.example.adapters.out.details.BooksModelRepository;
import org.example.application.dtos.BookDTO;
import org.example.application.ports.out.BooksRepositoryPort;

public class JPABooksRepositoryAdapter implements BooksRepositoryPort {

    private BooksModelRepository repository;

    public JPABooksRepositoryAdapter(BooksModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookDTO create(BookDTO dto) {
        BookModel model = BookModelTranslator.fromDTO(dto);

        model = repository.save(model);

        return BookModelTranslator.toDTO(model);
    }

    @Override
    @Transactional
    public BookDTO updateByIsbn(BookDTO dto) {
        if (dto.getIsbn() == null) {
            throw new RuntimeException("Isbn cannot be null");
        }

        Optional<BookModel> optModel = repository.findByIsbn(dto.getIsbn());
        if (!optModel.isPresent()) {
            throw new RuntimeException("No book exist with specified isbn " + dto.getIsbn());
        }

        BookModel model = optModel.get();
        model.setIsbn(dto.getIsbn());
        model.setTitle(dto.getTitle());
        model.setAuthor(dto.getAuthor());
        model.setDescription(dto.getDescription());

        model = repository.save(model);

        return BookModelTranslator.toDTO(model);
    }

    @Override
    public List<BookDTO> findAll() {
        return repository.findAll().stream().map(BookModelTranslator::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> findByIsbn(String isbn) {
        Optional<BookModel> optModel = repository.findByIsbn(isbn);

        if (!optModel.isPresent()) {
            return Optional.<BookDTO>empty();
        }

        return Optional.of(BookModelTranslator.toDTO(optModel.get()));
    }
}
