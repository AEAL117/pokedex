package com.acepokedex.pokedex.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Swagger
 * The function of this are document the system
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Bean
        public Docket api(){
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.acepokedex.pokedex.web.controller"))
                    .build();
        }
}
