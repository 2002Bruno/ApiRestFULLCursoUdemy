package br.com.divinecode.restfullapiudemy.controller;

import br.com.divinecode.restfullapiudemy.data.vo.security.AccountCredentialsDTO;
import br.com.divinecode.restfullapiudemy.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Utilização do refresh token para atualizar tempo ou outras infromações")
    @PutMapping("/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {
        if (checkParamsToRefreshToken(username, refreshToken)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");

        ResponseEntity siginin = authService.refreshToken(username, refreshToken);

        if (Objects.isNull(siginin)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");

        return siginin;
    }

    private static boolean checkParamsToRefreshToken(String username, String refreshToken) {
        return Objects.isNull(refreshToken) || refreshToken.isBlank() || Objects.isNull(username) || username.isBlank();
    }

    private static boolean checkIfParamsIsNotNull(AccountCredentialsDTO accountCredentialsDTO) {
        return Objects.isNull(accountCredentialsDTO)
                || Objects.isNull(accountCredentialsDTO.getUserName())
                || accountCredentialsDTO.getUserName().isBlank()
                || Objects.isNull(accountCredentialsDTO.getPassword())
                || accountCredentialsDTO.getPassword().isBlank();
    }
}
