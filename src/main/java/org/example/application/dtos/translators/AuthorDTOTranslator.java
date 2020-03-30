package org.example.application.dtos.translators;

import org.example.application.dtos.AuthorDTO;
import org.example.application.exceptions.AuthorInvalidException;
import org.example.domain.Author;

public class AuthorDTOTranslator {

    public static Author fromDTO(AuthorDTO dto) throws AuthorInvalidException {
        return new Author(dto.getName());
    }

    public static AuthorDTO toDTO(Author model) {
        return new AuthorDTO(model.getName());
    }
}
