package org.example.adapters.repositories.models.translators;

import org.example.adapters.repositories.models.AuthorModel;
import org.example.application.dtos.AuthorDTO;

public class AuthorModelTranslator {

    public static AuthorModel fromDTO(AuthorDTO dto) {
        return new AuthorModel(dto.getName());
    }

    public static AuthorDTO toDTO(AuthorModel model) {
        return new AuthorDTO(model.getName());
    }
}
