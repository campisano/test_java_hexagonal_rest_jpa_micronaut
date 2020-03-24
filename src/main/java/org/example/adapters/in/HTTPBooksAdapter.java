package org.example.adapters.in;

import java.util.List;

import org.example.adapters.in.details.AddBookRequest;
import org.example.application.dtos.BookDTO;
import org.example.application.exceptions.IsbnAlreadyExistsException;
import org.example.application.exceptions.IsbnNotExistsException;
import org.example.application.ports.in.AddBookUseCasePort;
import org.example.application.ports.in.GetBookUseCasePort;
import org.example.application.ports.in.ListAllBooksUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

@Controller("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HTTPBooksAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPBooksAdapter.class);
    private AddBookUseCasePort addBookUseCase;
    private GetBookUseCasePort getBookUseCase;
    private ListAllBooksUseCasePort listAllBooksUseCase;

    public HTTPBooksAdapter(AddBookUseCasePort addBookUseCase, GetBookUseCasePort getBookUseCase,
            ListAllBooksUseCasePort listAllBooksUseCase) {
        this.addBookUseCase = addBookUseCase;
        this.getBookUseCase = getBookUseCase;
        this.listAllBooksUseCase = listAllBooksUseCase;
    }

    @Post("/")
    public HttpResponse<BookDTO> add(HttpRequest<AddBookRequest> request) {
        LOGGER.info("{}, body={}", request.toString(), request.getBody());

        if (!request.getBody().isPresent()) {
            LOGGER.error("request without body");
            return HttpResponse.status(HttpStatus.BAD_REQUEST);
        }

        AddBookRequest body = request.getBody().get();

        try {
            BookDTO book = addBookUseCase.execute(new BookDTO(body.isbn, body.title, body.author, body.description));
            LOGGER.info("created, book={}", book);
            return HttpResponse.created(book);
        } catch (IsbnAlreadyExistsException exception) {
            LOGGER.error("exception, message={}", exception.getMessage());
            return HttpResponse.status(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception exception) {
            LOGGER.error("exception, message={}", exception.toString());
            return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Get("/{isbn}")
    public HttpResponse<BookDTO> getByIsbn(HttpRequest<?> request, String isbn) {
        LOGGER.info("{}, isbn={}", request.toString(), isbn);

        try {
            BookDTO book = getBookUseCase.execute(isbn);
            LOGGER.info("ok, isbn={}", isbn);
            return HttpResponse.ok(book);
        } catch (IsbnNotExistsException exception) {
            LOGGER.error("exception, message={}", exception.getMessage());
            return HttpResponse.status(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            LOGGER.error("exception, message={}", exception.toString());
            return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Get("/")
    public HttpResponse<List<BookDTO>> listAll(HttpRequest<?> request) {
        LOGGER.info("{}", request.toString());

        try {
            List<BookDTO> books = listAllBooksUseCase.execute();
            LOGGER.info("ok");
            return HttpResponse.ok(books);
        } catch (Exception exception) {
            LOGGER.error("exception, message={}", exception.toString());
            return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}