package br.com.divinecode.restfullapiudemy.service;

import br.com.divinecode.restfullapiudemy.controller.BookController;
import br.com.divinecode.restfullapiudemy.domain.book.Book;
import br.com.divinecode.restfullapiudemy.domain.book.BookDTO.BookDTO;
import br.com.divinecode.restfullapiudemy.exeptions.RequiredObjectIsNullException;
import br.com.divinecode.restfullapiudemy.exeptions.ResourceNotFoundException;
import br.com.divinecode.restfullapiudemy.mapper.DozerMapper;
import br.com.divinecode.restfullapiudemy.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private DozerMapper dozerMapper;

    public List<BookDTO> findAll() {
        var bookDTOList = DozerMapper.parseListObject(bookRepository.findAll(), BookDTO.class);

        bookDTOList.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getId())).withSelfRel()));
        return bookDTOList;
    }

    public BookDTO findById(Long id) {
        Book bookById = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        BookDTO bookDTO = dozerMapper.parseObject(bookById, BookDTO.class);

        bookDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookDTO;
    }

    public BookDTO create(BookDTO bookDTO) {

        if(bookDTO == null) throw new RequiredObjectIsNullException();

        Book bookConverted = dozerMapper.parseObject(bookDTO, Book.class);

        Book bookSaved = bookRepository.save(bookConverted);

        BookDTO bookDTOSaved = dozerMapper.parseObject(bookSaved, BookDTO.class);
//        bookDTOSaved.add(linkTo(methodOn(BookController.class).findById(bookDTOSaved.getId())).withSelfRel());
        return bookDTOSaved;
    }

    public BookDTO update(BookDTO bookDTO) {

        if(bookDTO == null) throw new RequiredObjectIsNullException();

        Book book = dozerMapper.parseObject(bookDTO, Book.class);
        Book bookSaved = bookRepository.saveAndFlush(book);

        BookDTO bookDTOSaved = dozerMapper.parseObject(bookSaved, BookDTO.class);
        bookDTOSaved.add(linkTo(methodOn(BookController.class).findById(bookDTOSaved.getId())).withSelfRel());
        return bookDTOSaved;
    }

    public void delete(Long id) {
        BookDTO bookById = findById(id);

        if (Objects.nonNull(bookById)) {
            bookRepository.deleteById(id);
        }
    }
}
