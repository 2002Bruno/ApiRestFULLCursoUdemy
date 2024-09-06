package br.com.divinecode.restfullapiudemy.domain.book.BookDTO;

import br.com.divinecode.restfullapiudemy.domain.person.Person;
import br.com.divinecode.restfullapiudemy.domain.person.personDTO.PersonDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
//Altera a ordem das variáveis na hora da resposta das requisições
@JsonPropertyOrder({"id", "title", "description", "author", "price"})
public class BookDTO extends RepresentationModel<PersonDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String description;

    private Person author;

    private Double price;

    public BookDTO() {}
}