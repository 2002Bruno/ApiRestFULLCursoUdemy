package br.com.divinecode.restfullapiudemy.config;

import br.com.divinecode.restfullapiudemy.security.jwt.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfig {

    //Configuração do filterChain da aplicação para interceptação alternada das requisições
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, JwtTokenFilter jwtTokenFilter) throws Exception {

        //Desabilitando o cors e csrf
        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //Configuração para permissão do acesso as url's
        //para o login, documentação do swagger, consumo das api's e
        //configuração do gerênciamento de sessão da aplicação
        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/signin", "/auth/refresh/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/users").denyAll());

        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    //Configuração do authenticationManager para entrega de solicitações de autenticação
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Configuração do BCrypt para criptografia de senhas
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
