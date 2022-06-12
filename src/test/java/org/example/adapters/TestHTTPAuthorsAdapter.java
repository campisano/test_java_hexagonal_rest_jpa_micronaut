package org.example.adapters;

import java.time.Duration;
import java.util.Arrays;

import org.example.TestUtils;
import org.example.application.dtos.AuthorDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.context.ApplicationContext;
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
    public void post() throws Exception {
        // Act
        var author = new AuthorDTO("author1");
        var response = TestUtils.JsonRequest().body(author).post("/v1/authors");

        // Assert
        Assertions.assertEquals(201, response.getStatusCode());
        Assertions.assertEquals(TestUtils.toJson(author), TestUtils.toJson(response.jsonPath().prettify()));
    }

    @Test
    public void postAlreadyExistent() {
        // Arrange
        var a1 = new AuthorDTO("author1");
        var a2 = new AuthorDTO("author2");
        Arrays.asList(a1, a2).forEach(a -> {
            TestUtils.JsonRequest().body(a).post("/v1/authors");
        });

        // Act
        var author = new AuthorDTO("author1");
        var response = TestUtils.JsonRequest().body(author).post("/v1/authors");

        // Assert
        Assertions.assertEquals(422, response.getStatusCode());
        Assertions.assertEquals("", response.getBody().asString());
    }
}
