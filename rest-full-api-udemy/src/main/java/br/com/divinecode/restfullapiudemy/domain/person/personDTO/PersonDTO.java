package br.com.divinecode.restfullapiudemy.domain.person.personDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
//Altera a ordem das variáveis na hora da resposta das requisições
@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {

    private static final long SerialVersionUID = 1L;

    //altera o nome da variável para resposta das requisições
    @JsonProperty("id")
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    public PersonDTO() {}
}
