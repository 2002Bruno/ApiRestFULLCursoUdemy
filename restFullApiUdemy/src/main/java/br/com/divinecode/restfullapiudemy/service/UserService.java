package br.com.divinecode.restfullapiudemy.service;

import br.com.divinecode.restfullapiudemy.controller.PersonController;
import br.com.divinecode.restfullapiudemy.domain.person.Person;
import br.com.divinecode.restfullapiudemy.domain.person.personDTO.PersonDTO;
import br.com.divinecode.restfullapiudemy.exeptions.RequiredObjectIsNullException;
import br.com.divinecode.restfullapiudemy.exeptions.ResourceNotFoundException;
import br.com.divinecode.restfullapiudemy.mapper.DozerMapper;
import br.com.divinecode.restfullapiudemy.repositories.PersonRepository;
import br.com.divinecode.restfullapiudemy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (Objects.nonNull(user)) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username" + username + " not found!");
        }
    }
}
