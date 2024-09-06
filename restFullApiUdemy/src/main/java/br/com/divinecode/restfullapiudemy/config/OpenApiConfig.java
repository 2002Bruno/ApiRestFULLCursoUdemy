package br.com.divinecode.restfullapiudemy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Classe para configuração da documentação do openAPI
@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("RestFull API com java 21 e spring boot 3.3.3")
                        .version("v1")
                        .description("Desenvolvido pelo curos da Udemy")
                        .termsOfService("https://teste.url.com.br/meus-cursos")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://teste.url.com.br/meus-cursos")
                        )
                );
    }
}
