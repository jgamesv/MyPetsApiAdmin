package it.jorge.MyPetsWeb.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyPetsConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("My Pets Web API")
                        .description("Api para la web administradora de la app My Pets ")
                        .contact(new Contact()
                                .name("Jorge")
                                .email("jorgevalenciaabad18@gmail.com")
                                .url("https://Jorge.com"))
                        .version("1.0"));


    }
}