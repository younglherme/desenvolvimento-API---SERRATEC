package org.serratec.serratecmusic.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Servidor Local");

        Contact contato = new Contact();
        contato.setEmail("guilhermeluizartes@hotmail.com");
        contato.setName("Guilherme Silva");
        contato.setUrl("https://www.linkedin.com/in/guilhermesilvaartes/");

        License apacheLicense = new License().name("Apache License Version 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info()
                .title("SerraTec-Music")
                .version("1.0")
                .contact(contato)
                .description("API desenvolvida para ter o controle de dos Artistas, Musicas e playlists.")
                .termsOfService("https://www.serratec.org.br/")
                .license(apacheLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}