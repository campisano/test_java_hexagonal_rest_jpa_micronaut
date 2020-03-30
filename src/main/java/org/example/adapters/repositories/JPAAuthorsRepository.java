package org.example.adapters.repositories;

import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

import org.example.adapters.repositories.models.AuthorModel;
import org.example.adapters.repositories.models.translators.AuthorModelTranslator;
import org.example.application.dtos.AuthorDTO;
import org.example.application.ports.out.AuthorsRepositoryPort;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

@Singleton
public class JPAAuthorsRepository implements AuthorsRepositoryPort {

    private MicronautAuthorsRepository repository;

    public JPAAuthorsRepository(MicronautAuthorsRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthorDTO create(AuthorDTO dto) {
        AuthorModel model = AuthorModelTranslator.fromDTO(dto);

        model = repository.save(model);

        return AuthorModelTranslator.toDTO(model);
    }

    @Override
    public Optional<AuthorDTO> findByName(String name) {
        Optional<AuthorModel> optModel = repository.findByName(name);

        if (!optModel.isPresent()) {
            return Optional.<AuthorDTO>empty();
        }

        return Optional.of(AuthorModelTranslator.toDTO(optModel.get()));
    }
}

@Repository
interface MicronautAuthorsRepository extends GenericRepository<AuthorModel, Long> {
    public List<AuthorModel> findAll();

    public Optional<AuthorModel> findByName(String name);

    public AuthorModel save(AuthorModel model);
}
