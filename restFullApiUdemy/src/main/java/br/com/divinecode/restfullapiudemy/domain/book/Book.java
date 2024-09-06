package br.com.divinecode.restfullapiudemy.domain.book;

import br.com.divinecode.restfullapiudemy.domain.person.Person;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 60)
    private String title;

    @Column(nullable = false, length = 400)
    private String description;

    @JoinColumn(name = "author_id")
    @OneToOne
    private Person author;

    @Column(nullable = false)
    private Double price;

    public Book() {}
}
