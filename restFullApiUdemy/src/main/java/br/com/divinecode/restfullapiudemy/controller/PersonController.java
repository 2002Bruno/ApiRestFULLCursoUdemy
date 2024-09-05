package br.com.divinecode.restfullapiudemy.controller;

import br.com.divinecode.restfullapiudemy.domain.person.personDTO.PersonDTO;
import br.com.divinecode.restfullapiudemy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    //E necessario deixar a configuracao produces = MediaType.APPLICATION_JSON_VALUE para a integracao com o Swagger
    @GetMapping(value = "/find-by-id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<PersonDTO> findById(@PathVariable(value = "id") Long id) {
        PersonDTO person = personService.findById(id);

        return ResponseEntity.ok(person);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> personList = personService.findAll();

        return ResponseEntity.ok().body(personList);
    }

    @PostMapping(value = "/create",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
        PersonDTO newPerson = personService.create(person);

        return ResponseEntity.ok(newPerson);
    }

    @PostMapping(value = "/update",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
        PersonDTO newPerson = personService.update(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

