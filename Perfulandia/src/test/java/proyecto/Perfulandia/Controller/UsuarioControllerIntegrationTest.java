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

// PASO 9, importar las clases necesarias para realizar las peticiones HTTP simuladas
// POST, GET, DELTE, PUT
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// PASO 10, importar las clases necesarias para verificar los resultados de las peticiones
// http simuladas
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// PASO 11, importar "any" para simular los argumentos en los metodos del servicio de ususario
import static org.mockito.ArgumentMatchers.any;

// PASO 12, importar mockito para simular el comportamiento de los metodos del servicio usuario con when
import static org.mockito.Mockito.when;

// PASO 13, importar JAVA util Optional
import java.util.Optional;


// usar la anotacion WebMvctest para crear una prueba a un controlador especifico
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerIntegrationTest {
    
    @Autowired
    //Inyectar MockMvc para realizar las operaciones HTTP simuladas
    //Lo creamos con un alias para poder usarlo en nuestor servicio
    private MockMvc mockMvc;    
    @MockBean
    private UsuarioService usuarioService;

    //Usar ObjectMapper para convertir los objetos a JSON
    @Autowired
    private ObjectMapper objectMapper;

    //Test para simular el registro de un nuevo usuari
    @Test
    void registraUsuario_retornaGuardar() throws Exception{
        usuario newUser = new usuario(); //Crear una instancia de usuario
        newUser.setNombre("Camilo");//Establecer nombre
        newUser.setNombre("camilo@gmail.com");//Establecer email
        newUser.setNombre("1234");//Establecer contra
        //Todo esto es una simulacion

        //TODO HASTA AHORA ES LA CAPA MODELO, AHORA VAMOS A SIMULAR EL 
        //COMPORTAMIENTO DEL METODO REGISTRAR DE SERVICE
        when(usuarioService.registrar(any(usuario.class)))
            .thenReturn(newUser);
    }   
}
