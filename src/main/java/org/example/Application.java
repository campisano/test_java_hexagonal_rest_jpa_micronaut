package org.example;

import javax.inject.Singleton;

import org.example.application.ports.out.BooksRepositoryPort;
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
    public SingletonAddBookUseCase(BooksRepositoryPort booksRepository) {
        super(booksRepository);
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