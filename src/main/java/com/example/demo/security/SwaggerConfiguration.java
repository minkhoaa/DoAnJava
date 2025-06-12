package com.example.demo.security;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Java Project Api", version = "v1"),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name =  "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "Bearer",
        bearerFormat = "JWT"
)

public class SwaggerConfiguration {
        @Value("${swagger.server-url:}")
        private String swaggerServerUrl;

        @Bean
        public OpenAPI customOpenAPI() {
                String serverUrl = swaggerServerUrl;
                if (serverUrl == null || serverUrl.isEmpty()) {
                        serverUrl = "http://localhost:8080";
                }
                return new OpenAPI().addServersItem(new Server().url(serverUrl));
        }
}
