package org.example;

import javax.inject.Singleton;

import org.example.application.ports.out.AuthorsRepositoryPort;
import org.example.application.ports.out.BooksRepositoryPort;
import org.example.application.usecases.AddAuthorUseCase;
import org.example.application.usecases.AddBookUseCase;
import org.example.application.usecases.GetBookUseCase;
import org.example.application.usecases.ListAllBooksUseCase;

import io.micronaut.runtime.Micronaut;

public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}

@Singleton
class SingletonAddBookUseCase extends AddBookUseCase {
    public SingletonAddBookUseCase(BooksRepositoryPort booksRepository, AuthorsRepositoryPort authorsRepository) {
        super(booksRepository, authorsRepository);
    }

}

@Singleton
class SingletonGetBookUseCase extends GetBookUseCase {
    public SingletonGetBookUseCase(BooksRepositoryPort booksRepository) {
        super(booksRepository);
    }
}

@Singleton
class SingletonListAllBooksUseCase extends ListAllBooksUseCase {
    public SingletonListAllBooksUseCase(BooksRepositoryPort booksRepository) {
        super(booksRepository);
    }
}

@Singleton
class SingletonAddAuthorUseCase extends AddAuthorUseCase {
    public SingletonAddAuthorUseCase(AuthorsRepositoryPort authorsRepository) {
        super(authorsRepository);
    }
}