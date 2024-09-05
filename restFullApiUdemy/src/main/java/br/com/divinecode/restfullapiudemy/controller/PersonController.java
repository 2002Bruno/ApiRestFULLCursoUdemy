package br.com.divinecode.restfullapiudemy.controller;

import br.com.divinecode.restfullapiudemy.domain.person.Person;
import br.com.divinecode.restfullapiudemy.domain.person.personDTO.PersonDTO;
import br.com.divinecode.restfullapiudemy.service.PersonService;
import br.com.divinecode.restfullapiudemy.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    //E necessario deixar a configuracao produces = MediaType.APPLICATION_JSON para a integracao com o Swagger
    @GetMapping(value = "/find-by-id/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<PersonDTO> findById(@PathVariable(value = "id") Long id) {
        PersonDTO person = personService.findById(id);

        return ResponseEntity.ok(person);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> personList = personService.findAll();

        return ResponseEntity.ok().body(personList);
    }

    @PostMapping(value = "/create",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
        PersonDTO newPerson = personService.create(person);

        return ResponseEntity.ok(newPerson);
    }

    @PostMapping(value = "/update",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
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

