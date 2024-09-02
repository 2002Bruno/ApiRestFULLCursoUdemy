package br.com.divinecode.restfullapiudemy.domain.person.personDTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PersonDTO {

    private static final long SerialVersionUID = 1L;

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    public PersonDTO() {}
}
