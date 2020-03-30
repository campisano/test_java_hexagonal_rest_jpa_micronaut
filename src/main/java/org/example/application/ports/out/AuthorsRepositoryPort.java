package org.example.application.ports.out;

import java.util.Optional;

import org.example.application.dtos.AuthorDTO;

public interface AuthorsRepositoryPort {
    AuthorDTO create(AuthorDTO author);

    Optional<AuthorDTO> findByName(String name);
}