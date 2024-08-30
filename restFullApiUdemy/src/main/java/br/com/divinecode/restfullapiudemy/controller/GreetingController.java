package br.com.divinecode.restfullapiudemy.controller;

import br.com.divinecode.exeptions.ResourceNotFoundException;
import br.com.divinecode.restfullapiudemy.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Controller para criação de rotas da api
@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/some/{numberOne}/{numberTwo}")
    public Double some(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (!greetingService.isNumeric(numberOne) || !greetingService.isNumeric(numberTwo)) {
            throw new ResourceNotFoundException("Escreva um valor numério por favor");
        }
        return greetingService.convertToDouble(numberOne) + greetingService.convertToDouble(numberTwo);
    }

}
