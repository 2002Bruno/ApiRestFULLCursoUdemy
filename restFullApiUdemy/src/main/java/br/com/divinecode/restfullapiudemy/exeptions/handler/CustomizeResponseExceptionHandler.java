package br.com.divinecode.restfullapiudemy.exeptions.handler;

import br.com.divinecode.restfullapiudemy.exeptions.ExeptionResponse;
import br.com.divinecode.restfullapiudemy.exeptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
@RestController
public class CustomizeResponseExceptionHandler extends ResponseEntityExceptionHandler {

    //Tratamento de Exceções genéricas
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExeptionResponse> handleAllExeptions(Exception ex, WebRequest request) {
        ExeptionResponse exeptionResponse = new ExeptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exeptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Tratamento de Exceções não suportadas
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExeptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
        ExeptionResponse exeptionResponse = new ExeptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exeptionResponse, HttpStatus.NOT_FOUND);
    }
}
