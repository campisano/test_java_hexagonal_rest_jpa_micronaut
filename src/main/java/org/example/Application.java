package org.example;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.example.application.ports.out.AuthorsRepositoryPort;
import org.example.application.ports.out.BooksRepositoryPort;
import org.example.application.usecases.AddAuthorUseCase;
import org.example.application.usecases.AddBookUseCase;
import org.example.application.usecases.GetBookUseCase;
import org.example.application.usecases.ListAllBooksUseCase;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.runtime.Micronaut;

public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}

@Factory
class InjectionProvider {

    private AddBookUseCase addBookUseCase;
    private GetBookUseCase getBookUseCase;
    private ListAllBooksUseCase listAllBooksUseCase;
    private AddAuthorUseCase addAuthorUseCase;

    @Inject
    public InjectionProvider(AuthorsRepositoryPort authorsRepository, BooksRepositoryPort booksRepository) {
        addBookUseCase = new AddBookUseCase(booksRepository, authorsRepository);
        getBookUseCase = new GetBookUseCase(booksRepository);
        listAllBooksUseCase = new ListAllBooksUseCase(booksRepository);
        addAuthorUseCase = new AddAuthorUseCase(authorsRepository);
    }

    @Bean
    @Singleton
    public AddBookUseCase getAddBookUseCase() {
        return addBookUseCase;
    }

    @Bean
    @Singleton
    public GetBookUseCase getGetBookUseCase() {
        return getBookUseCase;
    }

    @Bean
    @Singleton
    public ListAllBooksUseCase getListAllBooksUseCase() {
        return listAllBooksUseCase;
    }

    @Bean
    @Singleton
    public AddAuthorUseCase getAddAuthorUseCase() {
        return addAuthorUseCase;
    }
}
