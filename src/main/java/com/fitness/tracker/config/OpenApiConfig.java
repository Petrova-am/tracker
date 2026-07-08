package com.fitness.tracker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fitness Tracker API")
                        .version("1.0.0")
                        .description("REST API для фитнес-трекера. " +
                                "Позволяет управлять пользователями, тренировками и получать статистику.")
                        .contact(new Contact()
                                .name("Petrova AM")
                                .email("apetrova2006@gmail.com")
                                .url("https://github.com/Petrova-am"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}