package org.example.application.ports;

import java.util.Optional;

import org.example.application.dtos.AuthorDTO;
import org.example.application.ports.out.AuthorsRepositoryPort;

public class MockedAuthorsRepositoryPort implements AuthorsRepositoryPort {
    public AuthorDTO create_in;
    public AuthorDTO create_out;

    public String findByName_in;
    public Optional<AuthorDTO> findByName_out;

    @Override
    public AuthorDTO create(AuthorDTO author) {
        create_in = author;
        return create_out;
    }

    @Override
    public Optional<AuthorDTO> findByName(String name) {
        findByName_in = name;
        return findByName_out;
    }
}
