



import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
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
        contato.setEmail("romulodeoliveiralima@gmail.com");
        contato.setName("Guilherme Luiz da Silva");
        contato.setUrl("https://www.linkedin.com/in/guilhermesilvaartes/");

        License apacheLicense = new License().name("Apache License Version 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        Info info =  new Server()
                .title("API - Controle de Manutenção de Veículos")
                .version("1.0")
                .contact(contato)
                .description("API desenvolvida para ter o controle de manutenções de veículos.")
                .termsOfService("https://www.serratec.org.br/")
                .license(apacheLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}