package org.example.adapters.controllers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.application.ports.dtos.BookDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.context.ApplicationContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.DefaultHttpClient;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.runtime.server.EmbeddedServer;

public class TestBookController {

    private EmbeddedServer server;
    private DefaultHttpClient client;

    @BeforeEach
    public void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = new DefaultHttpClient(server.getURL());
        client.getConfiguration().setExceptionOnErrorStatus(false);
        client.getConfiguration().setReadTimeout(Duration.ofSeconds(600));
    }

    @AfterEach
    public void cleanup() {
        client.close();
        server.stop();
    }

    @Test
    public void getAllWhenEmpty() {
        HttpRequest<?> request = HttpRequest.GET("/books");

        HttpResponse<?> response = client.toBlocking().exchange(request, Argument.listOf(BookDTO.class));

        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
        Assertions.assertEquals(new ArrayList<BookDTO>(), response.body());
    }

    @Test
    public void getAllWhenExists() {
        BookDTO b1 = new BookDTO("isbn1", "title1", "author1", "description1");
        BookDTO b2 = new BookDTO("isbn2", "title2", "author2", "description2");
        postBooks(Arrays.asList(b1, b2));
        HttpRequest<?> request = HttpRequest.GET("/books");

        HttpResponse<List<BookDTO>> response = client.toBlocking().exchange(request, Argument.listOf(BookDTO.class));

        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(2, response.body().size());
        Assertions.assertEquals(response.body().get(0).toString(), b1.toString());
        Assertions.assertEquals(response.body().get(1).toString(), b2.toString());
    }

    @Test
    public void getOneWhenEmpty() {
        HttpRequest<?> request = HttpRequest.GET("/books/unexistent_isbn");

        HttpResponse<JsonError> response = client.toBlocking().exchange(request, Argument.of(JsonError.class));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
        Assertions.assertEquals(false, response.getBody().isPresent());
    }

    @Test
    public void getOneWhenExist() {
        BookDTO b1 = new BookDTO("isbn1", "title1", "author1", "description1");
        BookDTO b2 = new BookDTO("isbn2", "title2", "author2", "description2");
        postBooks(Arrays.asList(b1, b2));
        HttpRequest<?> request = HttpRequest.GET("/books/isbn1");

        HttpResponse<BookDTO> response = client.toBlocking().exchange(request, Argument.of(BookDTO.class));

        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
        Assertions.assertEquals(b1.toString(), response.body().toString());
    }

    @Test
    public void post() {
        BookDTO requestBody = new BookDTO("isbn1", "title1", "author1", "description1");
        HttpRequest<BookDTO> request = HttpRequest.POST("/books", requestBody);

        HttpResponse<BookDTO> response = client.toBlocking().exchange(request, Argument.of(BookDTO.class));

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatus());
        Assertions.assertEquals(requestBody.toString(), response.body().toString());
    }

    @Test
    public void postWhenAlreadyExist() {
        BookDTO b1 = new BookDTO("isbn1", "title1", "author1", "description1");
        BookDTO b2 = new BookDTO("isbn2", "title2", "author2", "description2");
        postBooks(Arrays.asList(b1, b2));

        BookDTO requestBody = new BookDTO("isbn1", "title1", "author1", "description1");
        HttpRequest<BookDTO> request = HttpRequest.POST("/books", requestBody);

        HttpResponse<JsonError> response = client.toBlocking().exchange(request, Argument.of(JsonError.class));

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatus());
        Assertions.assertEquals(false, response.getBody().isPresent());
    }

    private void postBooks(List<BookDTO> books) {
        books.forEach(b -> {
            client.toBlocking().exchange(HttpRequest.POST("/books", b), Argument.of(BookDTO.class));
        });
    }
}
