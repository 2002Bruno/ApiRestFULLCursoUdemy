package br.com.divinecode.restfullapiudemy.controller;

import br.com.divinecode.restfullapiudemy.domain.person.personDTO.PersonDTO;
import br.com.divinecode.restfullapiudemy.service.PersonService;
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
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endipoints for Managing Peoples")
public class PersonController {

    @Autowired
    private PersonService personService;

    //E necessario deixar a configuracao produces = MediaType.APPLICATION_JSON_VALUE para a integracao com o Swagger
    @GetMapping(value = "/find-by-id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Buscar Pessoa por id", description = "Tras uma pessoa pelo id",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                            @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ResponseEntity<PersonDTO> findById(@PathVariable(value = "id") Long id) {
        PersonDTO person = personService.findById(id);

        return ResponseEntity.ok(person);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Listagem de Pessoas", description = "Tras uma lista de pessoas",
            tags = {"People"},
            responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                    )}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> personList = personService.findAll();

        return ResponseEntity.ok().body(personList);
    }

    @PostMapping(value = "/create",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Cadastro de pessoa", description = "Cadastro de uma nova pessoa",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
        PersonDTO newPerson = personService.create(person);

        return ResponseEntity.ok(newPerson);
    }

    @PostMapping(value = "/update",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados de uma pessoa já existente",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
        PersonDTO newPerson = personService.update(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deletar Pessoa por id", description = "Deleta uma pessoa já existente pelo seu id",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

