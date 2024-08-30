package br.com.divinecode.restfullapiudemy.service;

import br.com.divinecode.exeptions.ResourceNotFoundException;
import br.com.divinecode.restfullapiudemy.domain.Person;
import br.com.divinecode.restfullapiudemy.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person findById(Long id) {
        Person personById = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return personById;
    }

    public Person create(Person person) {

        return personRepository.save(person);
    }

    public Person update(Person person) {
        Person newPerson = findById(person.getId());

        newPerson.setId(person.getId());
        newPerson.setFirstName(person.getFirstName());
        newPerson.setLastName(person.getLastName());
        newPerson.setAddress(person.getAddress());
        newPerson.setGender(person.getGender());

        return personRepository.save(newPerson);
    }

    public void delete(Long id) {
        Person personById = findById(id);

        if (Objects.nonNull(personById)) {
            personRepository.deleteById(id);
        }
    }

    public List<Person> findAll() {
        List<Person> personList = personRepository.findAll();

        return personList;
    }
}
