package org.example;

import javax.inject.Named;
import javax.inject.Singleton;

import org.example.application.ports.out.BooksRepositoryPort;
import org.example.application.usecases.AddBookUseCase;
import org.example.application.usecases.GetBookUseCase;
import org.example.application.usecases.ListAllBookUseCase;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.runtime.Micronaut;

public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}

@Factory
class BeanFactory {

    @Bean
    @Named
    AddBookUseCase createAddBook(@Singleton BooksRepositoryPort booksRepository) {
        return new AddBookUseCase(booksRepository);
    }

    @Bean
    @Named
    GetBookUseCase createGetBookUseCase(@Singleton BooksRepositoryPort booksRepository) {
        return new GetBookUseCase(booksRepository);
    }

    @Bean
    @Named
    ListAllBookUseCase createListAllBookUseCase(@Singleton BooksRepositoryPort booksRepository) {
        return new ListAllBookUseCase(booksRepository);
    }
}