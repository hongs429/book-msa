package com.msa.rental.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rental Service API")
                        .description("도서 대여 서비스 API 명세서")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Rental Service Team")
                                .email("rental@msa.com")));
    }
}
