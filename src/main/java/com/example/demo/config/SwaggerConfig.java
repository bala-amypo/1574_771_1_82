package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotationConfiguration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("Remote Work Productivity API").version("1.0"));
    }
}
