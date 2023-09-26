package com.adatech.cielo.prospect.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Value("${projeto.nome}")
    private String nomeProjeto;

    @Value("${projeto.descricao}")
    private String descricao;
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(nomeProjeto)
                        .description(descricao)
                        .contact(new Contact()
                                .name("Marcos Sousa")
                                .email("backend@adatech.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://empresa/api/licenca")));
    }

}
