package br.com.divinecode.restfullapiudemy.controller;

import br.com.divinecode.restfullapiudemy.data.vo.security.AccountCredentialsDTO;
import br.com.divinecode.restfullapiudemy.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth/")
@Tag(name = "Endpoints de autenticação")
public class AuthController {

    @Autowired
    private AuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticação do usuário e retorno do token")
    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody AccountCredentialsDTO accountCredentialsDTO) {
        if (checkIfParamsIsNotNull(accountCredentialsDTO)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");

        ResponseEntity siginin = authService.siginin(accountCredentialsDTO);

        if (Objects.isNull(siginin)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");

        return siginin;
    }

    private static boolean checkIfParamsIsNotNull(AccountCredentialsDTO accountCredentialsDTO) {
        return Objects.isNull(accountCredentialsDTO)
                || Objects.isNull(accountCredentialsDTO.getUserName())
                || accountCredentialsDTO.getUserName().isBlank()
                || Objects.isNull(accountCredentialsDTO.getPassword())
                || accountCredentialsDTO.getPassword().isBlank();
    }
}
