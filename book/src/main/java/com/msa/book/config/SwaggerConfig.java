package com.msa.book.config;

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
                        .title("Book Service API")
                        .description("도서 관리 서비스 API 명세서")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Book Service Team")
                                .email("book@msa.com")));
    }
}

