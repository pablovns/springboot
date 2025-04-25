package com.pablovns.springboot.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static Contact getContact() {
        return new Contact()
                .name("Pablo")
                .url("https://github.com/pablovns")
                .email("viniciuspablo962@gmail.com");
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API com Spring Boot")
                        .description("API Rest com integração ao banco de dados PostgreSQL")
                        .version("1.0")
                        .contact(getContact())
                );
    }
}
