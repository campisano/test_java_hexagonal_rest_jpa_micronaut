package org.example.adapters.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.example.adapters.repositories.models.AuthorModel;
import org.example.adapters.repositories.models.translators.AuthorModelTranslator;
import org.example.application.dtos.AuthorDTO;
import org.example.application.ports.out.AuthorsRepositoryPort;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

@Singleton
public class JPAAuthorsRepository implements AuthorsRepositoryPort {

    private MicronautAuthorsRepository authorsRepository;

    public JPAAuthorsRepository(MicronautAuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    @Transactional
    @Override
    public AuthorDTO create(AuthorDTO dto) {
        var model = AuthorModelTranslator.fromDTO(dto);

        model = authorsRepository.save(model);

        return AuthorModelTranslator.toDTO(model);
    }

    @Override
    public Optional<AuthorDTO> findByName(String name) {
        var optModel = authorsRepository.findByName(name);

        if (!optModel.isPresent()) {
            return Optional.<AuthorDTO>empty();
        }

        return Optional.of(AuthorModelTranslator.toDTO(optModel.get()));
    }

    @Override
    public Set<AuthorDTO> findByNameIn(Set<String> authorNames) {
        return AuthorModelTranslator.toDTO(authorsRepository.findByNameIn(authorNames));
    }
}

@Repository
interface MicronautAuthorsRepository extends GenericRepository<AuthorModel, Long> {
    public List<AuthorModel> findAll();

    public Optional<AuthorModel> findByName(String name);

    public AuthorModel save(AuthorModel model);

    public Set<AuthorModel> findByNameIn(Set<String> authors);
}
