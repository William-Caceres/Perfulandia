package proyecto.Perfulandia.Controller;

// PASO 1, integrar las importaciones necesarias, las mismas que el controlador a testear
import proyecto.Perfulandia.Model.usuario;
import proyecto.Perfulandia.Service.UsuarioService;

// PASO 2, importar ObjectMapper para convertir objetos de simulacion a JSON
import com.fasterxml.jackson.databind.ObjectMapper;

// PASO 3, importar anotaciones con las que vamos a trabajar, de las pruebas JUnit
import org.junit.jupiter.api.Test;

// PASO 4, importar las anotaciones spring para inyectar las dependencias Maven
import org.springframework.beans.factory.annotation.Autowired;

// PASO 5, importar la anotacion para las pruebas de los controladores web (API)
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

// PASO 6, importar la anotacion para simular los beans de Spring
import org.springframework.boot.test.mock.mockito.MockBean;

//-----------------
// PASO 7, importar el tipo de contenido Mediatype para las peticiones http
//estas peticiones se hacen con el controller y la api
import org.springframework.http.MediaType;

// PASO 8, importar MockMvc para realizar peticiones http
import org.springframework.test.web.servlet.MockMvc;

// PASO 9, 


public class UsuarioControllerIntegrationTest {
    
}
