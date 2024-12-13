package org.ecommerce.app.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AppBeansConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.setAllowedHeaders(
                List.of(
                        HttpHeaders.ORIGIN,
                        HttpHeaders.AUTHORIZATION,
                        HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.ACCEPT)
        );
        config.setAllowedMethods(
                List.of(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.OPTIONS.name()
                )
        );
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    @Bean
    public OpenAPI openAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Server URL in the development environment");

        Server prodServer = new Server();
        prodServer.setUrl("http://localhost:8080");
        prodServer.setDescription("Server URL in the production environment");

        Contact contact = new Contact();
        contact.setEmail("developer.baz@gmail.com");
        contact.setName("Ahmed Baz");
        contact.setUrl("https://github.com/ahmed-baz");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        final String description = """
                This is a Spring Boot application that demonstrates how to build a RESTful service with CRUD operations,
                using the power of Spring Security with OAuth server keycloak for authentication and authorization, and use PostgreSQL for data persistence.
                The service manages CRUD operations for orders, and products.
                """;
        Info info = new Info()
                .title("Order Service API")
                .version("1.0.0")
                .contact(contact)
                .description(description)
                .termsOfService("https://github.com/ahmed-baz/order-service/blob/master/README.md")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }


}
