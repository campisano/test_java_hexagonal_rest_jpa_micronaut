package org.example.application.ports.in;

import java.text.MessageFormat;

public class IsbnAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IsbnAlreadyExistsException(String isbn) {
        super(MessageFormat.format("Book with isbn {0} already exists", isbn));
    }
}
