package br.com.divinecode.restfullapiudemy.service;

import br.com.divinecode.restfullapiudemy.data.vo.security.AccountCredentialsDTO;
import br.com.divinecode.restfullapiudemy.data.vo.security.TokenDTO;
import br.com.divinecode.restfullapiudemy.domain.user.User;
import br.com.divinecode.restfullapiudemy.repositories.UserRepository;
import br.com.divinecode.restfullapiudemy.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity siginin(AccountCredentialsDTO accountCredentialsDTO) {
        try {

            //Recebemos o accountCredentialsDTO e tentamos fazer o login pelo authenticationManager
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountCredentialsDTO.getUserName(), accountCredentialsDTO.getPassword()));

            //Acessamos o repositório buscando o usuário pelo username
            User user = userRepository.findByUsername(accountCredentialsDTO.getUserName());

            //criamos uma nova instância do TokenDTO para retornalo
            var tokenResponse = new TokenDTO();

            //verificamos se o usuário foi encontrado
            if (Objects.nonNull(user)) {

                //se o usuario for encontrado, geramos o token de acesso
                tokenResponse = tokenProvider.createAccessToken(user.getUsername(), user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username not found!");
            }

            //retornamos o token
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String userName, String refreshToken) {
        //Acessamos o repositório buscando o usuário pelo username
        User user = userRepository.findByUsername(userName);

        //criamos uma nova instância do TokenDTO para retornalo
        var tokenResponse = new TokenDTO();

        //verificamos se o usuário foi encontrado
        if (Objects.nonNull(user)) {
            //se o usuario for encontrado, geramos o token de acesso
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username not found!");
        }
        //retornamos o token
        return ResponseEntity.ok(tokenResponse);
    }
}
