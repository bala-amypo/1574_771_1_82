package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        SecurityScheme bearerScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                // ✅ Server URL (keep yours)
                .servers(List.of(
                        new Server().url("https://9010.32procr.amypo.ai/")
                ))

                // ✅ Add JWT security scheme
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", bearerScheme)
                )

                // ✅ Apply security globally
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerAuth"));
    }
}
