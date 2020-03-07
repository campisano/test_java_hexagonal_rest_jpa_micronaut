package org.example.application.ports.in;

import java.text.MessageFormat;

public class IsbnNotExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IsbnNotExistsException(String isbn) {
        super(MessageFormat.format("Book with isbn {0} not exists", isbn));
    }
}
