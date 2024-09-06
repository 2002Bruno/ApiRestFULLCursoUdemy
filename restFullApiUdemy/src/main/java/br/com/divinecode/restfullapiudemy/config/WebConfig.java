package br.com.divinecode.restfullapiudemy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.originPatterns:default}")
    private String corsOriginPatters = "";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsOriginPatters.split(",");
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(allowedOrigins)
                .allowCredentials(true);
    }

    //Implementacao das configuracoes do content negotiation para retorno de json e xml
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        //Via QUERY PARAMETER
        configurer.
                favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);

        //Via HEADER
//        configurer.
//                favorParameter(false)
//                .ignoreAcceptHeader(false)
//                .useRegisteredExtensionsOnly(false)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("xml", MediaType.APPLICATION_XML)
//                .mediaType("json", MediaType.APPLICATION_JSON);
    }
}
