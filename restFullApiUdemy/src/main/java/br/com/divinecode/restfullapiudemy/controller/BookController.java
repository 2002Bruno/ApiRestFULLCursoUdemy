package br.com.divinecode.restfullapiudemy.controller;

import br.com.divinecode.restfullapiudemy.domain.book.BookDTO.BookDTO;
import br.com.divinecode.restfullapiudemy.domain.person.personDTO.PersonDTO;
import br.com.divinecode.restfullapiudemy.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Livro", description = "Endpoints para gerênciamento de livros")
public class BookController {

    @Autowired
    private BookService bookService;

    //E necessario deixar a configuracao produces = MediaType.APPLICATION_JSON_VALUE para a integracao com o Swagger
    @GetMapping(value = "/find-by-id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Buscar Livro por id", description = "Tras um livro pelo id",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ResponseEntity<BookDTO> findById(@PathVariable(value = "id") Long id) {
        BookDTO bookDTO = bookService.findById(id);

        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Listagem de Livros", description = "Tras uma lista de livros",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                            )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> bookList = bookService.findAll();

        return ResponseEntity.ok().body(bookList);
    }

    @PostMapping(value = "/create",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Cadastro de livro", description = "Cadastro de um novo livro",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO book) {
        BookDTO newBook = bookService.create(book);

        return ResponseEntity.ok(newBook);
    }

    @PostMapping(value = "/update",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Atualizar Livro", description = "Atualiza os dados de um livro já existente",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ResponseEntity<BookDTO> update(@RequestBody BookDTO book) {
        BookDTO newBook = bookService.update(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deletar Livro por id", description = "Deleta uma livro já existente pelo seu id",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        bookService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
