package org.example.adapters.controllers;

import org.example.application.dtos.AuthorDTO;
import org.example.application.exceptions.AuthorAlreadyExistsException;
import org.example.application.exceptions.AuthorInvalidException;
import org.example.application.ports.in.AddAuthorUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

@Controller("/v1/authors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HTTPAuthorsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPAuthorsController.class);

    private AddAuthorUseCasePort addAuthorUseCase;

    public HTTPAuthorsController(AddAuthorUseCasePort addAuthorUseCase) {
        this.addAuthorUseCase = addAuthorUseCase;
    }

    @Post("/")
    public HttpResponse<AuthorDTO> add(HttpRequest<AddAuthorRequest> request) {
        LOGGER.info("{}, body={}", request.toString(), request.getBody());

        if (!request.getBody().isPresent()) {
            LOGGER.error("request without body");

            return HttpResponse.status(HttpStatus.BAD_REQUEST);
        }

        var body = request.getBody().get();

        try {
            var author = addAuthorUseCase.execute(new AuthorDTO(body.getName()));

            LOGGER.info("created, author={}", author);

            return HttpResponse.created(author);
        } catch (AuthorInvalidException | AuthorAlreadyExistsException exception) {
            LOGGER.error("exception, message={}", exception.getMessage());

            return HttpResponse.status(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception exception) {
            LOGGER.error("exception, message={}", exception.toString());

            return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

@Introspected
class AddAuthorRequest extends AuthorDTO {

    public AddAuthorRequest(String name) {
        super(name);
    }
}
