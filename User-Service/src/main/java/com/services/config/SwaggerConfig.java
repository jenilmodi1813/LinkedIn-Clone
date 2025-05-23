package com.services.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI myConfig(){



        return new OpenAPI()
                .info(
                        new Info().title("Shopping Application")
                                .description("Microservice Project")
                )
                .servers(List.of(new Server().url("http://localhost:8081").description("localhost:8081"),
                        new Server().url("localhost:8082/").description("localhost:8082")
                ))
                .tags(
                        List.of(
                                new Tag().name("Product-Api")
                        )
                );
    }
}
