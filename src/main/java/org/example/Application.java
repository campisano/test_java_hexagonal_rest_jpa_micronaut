package org.example;

import javax.inject.Named;
import javax.inject.Singleton;

import org.example.application.ports.out.BookRepositoryPort;
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
    AddBookUseCase createAddBook(@Singleton BookRepositoryPort bookRepository) {
        return new AddBookUseCase(bookRepository);
    }

    @Bean
    @Named
    GetBookUseCase createGetBookUseCase(@Singleton BookRepositoryPort bookRepository) {
        return new GetBookUseCase(bookRepository);
    }

    @Bean
    @Named
    ListAllBookUseCase createListAllBookUseCase(@Singleton BookRepositoryPort bookRepository) {
        return new ListAllBookUseCase(bookRepository);
    }
}