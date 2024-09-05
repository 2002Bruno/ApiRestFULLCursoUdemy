package br.com.divinecode.restfullapiudemy.service;

import br.com.divinecode.restfullapiudemy.controller.PersonController;
import br.com.divinecode.restfullapiudemy.exeptions.ResourceNotFoundException;
import br.com.divinecode.restfullapiudemy.domain.person.Person;
import br.com.divinecode.restfullapiudemy.domain.person.personDTO.PersonDTO;
import br.com.divinecode.restfullapiudemy.mapper.DozerMapper;
import br.com.divinecode.restfullapiudemy.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private DozerMapper dozerMapper;

    public PersonDTO findById(Long id) {
        Person personById = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        PersonDTO personDTO = dozerMapper.parseObject(personById, PersonDTO.class);

        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    public PersonDTO create(PersonDTO personDTO) {
        Person personConverted = dozerMapper.parseObject(personDTO, Person.class);

        Person personSaved = personRepository.save(personConverted);

        PersonDTO personDTOSaved = dozerMapper.parseObject(personSaved, PersonDTO.class);
        personDTOSaved.add(linkTo(methodOn(PersonController.class).findById(personDTOSaved.getId())).withSelfRel());
        return personDTOSaved;
    }

    public PersonDTO update(PersonDTO personDTO) {
        Person person = dozerMapper.parseObject(personDTO, Person.class);
        Person personSaved = personRepository.saveAndFlush(person);

        PersonDTO personDTOSaved = dozerMapper.parseObject(personSaved, PersonDTO.class);
        personDTOSaved.add(linkTo(methodOn(PersonController.class).findById(personDTOSaved.getId())).withSelfRel());
        return personDTOSaved;
    }

    public void delete(Long id) {
        PersonDTO personById = findById(id);

        if (Objects.nonNull(personById)) {
            personRepository.deleteById(id);
        }
    }

    public List<PersonDTO> findAll() {
        var personDTOList = DozerMapper.parseListObject(personRepository.findAll(), PersonDTO.class);

        personDTOList.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));
        return personDTOList;
    }
}
