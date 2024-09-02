package br.com.divinecode.restfullapiudemy.service;

import br.com.divinecode.exeptions.ResourceNotFoundException;
import br.com.divinecode.restfullapiudemy.domain.person.Person;
import br.com.divinecode.restfullapiudemy.domain.person.personDTO.PersonDTO;
import br.com.divinecode.restfullapiudemy.mapper.DozerMapper;
import br.com.divinecode.restfullapiudemy.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        return personDTO;
    }

    public PersonDTO create(PersonDTO personDTO) {
        Person personConverted = dozerMapper.parseObject(personDTO, Person.class);

        Person personSaved = personRepository.save(personConverted);

        PersonDTO personDTOSaved = dozerMapper.parseObject(personSaved, PersonDTO.class);
        return personDTOSaved;
    }

    public PersonDTO update(PersonDTO personDTO) {
        Person person = dozerMapper.parseObject(personDTO, Person.class);
        Person personSaved = personRepository.saveAndFlush(person);

        PersonDTO personDTOSaved = dozerMapper.parseObject(personSaved, PersonDTO.class);
        return personDTOSaved;
    }

    public void delete(Long id) {
        PersonDTO personById = findById(id);

        if (Objects.nonNull(personById)) {
            personRepository.deleteById(id);
        }
    }

    public List<PersonDTO> findAll() {
        List<Person> personList = personRepository.findAll();
        List<PersonDTO> personDTOList = dozerMapper.parseListObject(personList, PersonDTO.class);

        return personDTOList;
    }
}
