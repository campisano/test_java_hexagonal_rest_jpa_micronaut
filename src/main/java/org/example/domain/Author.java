package org.example.domain;

import java.text.MessageFormat;

import org.example.application.exceptions.AuthorInvalidException;

public class Author {
    private String name;

    public Author(String name) throws AuthorInvalidException {
        ensureCreable(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static void ensureCreable(String name) throws AuthorInvalidException {
        if (name == null || name.length() == 0) {
            throw new AuthorInvalidException(MessageFormat.format("Name {0} is invalid", name));
        }
    }
}
