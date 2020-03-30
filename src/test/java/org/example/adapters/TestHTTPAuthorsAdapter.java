package org.example.adapters;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.example.application.dtos.AuthorDTO;
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
import io.micronaut.runtime.server.EmbeddedServer;

public class TestHTTPAuthorsAdapter {

    private EmbeddedServer server;
    private DefaultHttpClient client;

    @BeforeEach
    public void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = new DefaultHttpClient(server.getURL());
        client.getConfiguration().setExceptionOnErrorStatus(false);
        client.getConfiguration().setReadTimeout(Duration.ofSeconds(100));
    }

    @AfterEach
    public void cleanup() {
        client.close();
        server.stop();
    }

    @Test
    public void post() {
        AuthorDTO requestBody = new AuthorDTO("name1");
        HttpRequest<AuthorDTO> request = HttpRequest.POST("/v1/authors", requestBody);

        HttpResponse<DeserializableAuthorDTO> response = exchange(request, Argument.of(DeserializableAuthorDTO.class));

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatus());
        Assertions.assertEquals(requestBody.toString(), response.body().toString());
    }

    @Test
    public void postWhenAlreadyExist() {
        AuthorDTO a1 = new AuthorDTO("name1");
        AuthorDTO a2 = new AuthorDTO("name2");
        postBooks(Arrays.asList(a1, a2));

        AuthorDTO requestBody = new AuthorDTO("name1");
        HttpRequest<AuthorDTO> request = HttpRequest.POST("/v1/authors", requestBody);

        HttpResponse<DeserializableAuthorDTO> response = exchange(request, Argument.of(DeserializableAuthorDTO.class));

        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatus());
        Assertions.assertEquals(false, response.getBody().isPresent());
    }

    private void postBooks(List<AuthorDTO> authors) {
        authors.forEach(author -> {
            exchange(HttpRequest.POST("/v1/authors", author), Argument.of(DeserializableAuthorDTO.class));
        });
    }

    private <I, O> HttpResponse<O> exchange(HttpRequest<I> request, Argument<O> bodyType) {
        return client.toBlocking().exchange(request, bodyType, bodyType);
    }
}

class DeserializableAuthorDTO extends AuthorDTO {
    public DeserializableAuthorDTO() {
        super(null);
    }
}