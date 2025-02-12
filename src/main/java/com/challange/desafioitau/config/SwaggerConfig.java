package com.challange.desafioitau.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Configura o contato para a documentação
    private Contact contato() {
        return new Contact()
                .name("marley")
                .url("http://www.site.com.br")
                .email("marleypm16@gmail.com");
    }

    // Configura as informações da API
    private Info infoBuilder() {
        return new Info()
                .title("Api Desafio Itau")
                .description("Testando SpringDoc OpenAPI")
                .version("1.0.0")
                .contact(this.contato())
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
    }

    // Configura o OpenAPI (substitui o Docket do Springfox)
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(this.infoBuilder());
    }
}