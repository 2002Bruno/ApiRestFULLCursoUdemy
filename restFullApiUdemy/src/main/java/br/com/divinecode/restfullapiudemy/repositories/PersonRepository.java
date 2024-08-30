package br.com.divinecode.restfullapiudemy.repositories;

import br.com.divinecode.restfullapiudemy.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
