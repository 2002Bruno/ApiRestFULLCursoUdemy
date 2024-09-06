package br.com.divinecode.restfullapiudemy.repositories;

import br.com.divinecode.restfullapiudemy.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
