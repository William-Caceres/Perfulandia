package proyecto.Perfulandia.OpenApiConfig;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI PerfulandiaOpenAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("APIs - Perfulandia SPA")
                .description("Documentación de las APIs REST para gestionar los micro servicios, para gestionar el catalogo de PRODUCTOS, USUARIOS, CARRITO DE COMPRAS, NOTIFICACIONES e INCIDENCIAS")
                .version("v2.0"));
    }
}
