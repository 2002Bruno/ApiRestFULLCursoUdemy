package br.com.divinecode.restfullapiudemy.domain.book.BookDTO;

import br.com.divinecode.restfullapiudemy.domain.person.personDTO.PersonDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
//Altera a ordem das variáveis na hora da resposta das requisições
@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
public class BookDTO extends RepresentationModel<PersonDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String description;

    private String author;

    public BookDTO() {}
}