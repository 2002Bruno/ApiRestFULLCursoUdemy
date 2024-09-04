package br.com.divinecode.restfullapiudemy.domain.person.personDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import lombok.Data;

@Data

//Altera a ordem das variáveis na hora da resposta das requisições
@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
public class PersonDTO {

    private static final long SerialVersionUID = 1L;

    private Long id;

    //altera o nome da variável para resposta das requisições
    @JsonProperty("first_name")
    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    public PersonDTO() {}
}
