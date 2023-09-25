package com.challenge.customerprospecting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI dungeonsAndDragonsMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Customer Prospecting API")
                        .description("API Rest utilizando Java SpringBoot para prospecção de clientes")
                        .version("1.0"));
    }
}
