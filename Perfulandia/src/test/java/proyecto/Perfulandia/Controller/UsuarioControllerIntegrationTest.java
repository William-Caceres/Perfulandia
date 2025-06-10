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

            //Simular la peticion POST  de usuarioController para registrar un nuevo usuario
        mockMvc.perform(post("/api/v1/usuarios/registrar")//RUTA igual de usuario controller
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newUser)))
            .andExpect(status().isOk()) //verificamos que la respuesta al metodo sea un esto 200 OK
            .andExpect(jsonPath("$.nombre").value("Camilo"))  //verificamos que el nombre del usuario simulado sea correcto 
            .andExpect(jsonPath("$.email").value("camilo@gmail.com"))//verificamos que el correo del usuario simulado sea correcto
            .andExpect(jsonPath("$.password").value("1234"));//verificamos que la password del usuario simulado sea correcto
    }   
       
    //Test para simular el inicio de sesion de un usuario registrado
    @Test
    void loginUsuario_ReturnOK() throws Exception {
        usuario userExistente = new usuario();
        userExistente.setNombre("Camilo");
        userExistente.setEmail("camilo@gmail.com");
        userExistente.setPassword("1234");

        //Simular el comportamiento del metodo autenticar del usuarioService con un usuario registrado
        when(usuarioService.autenticar("camilo@gmail.com","1234"))
            .thenReturn(Optional.of(userExistente));

        //Simular la peticion POST en usuarioController para autenticar el login un usuario registrado
        mockMvc.perform(post("/api/v1/usuarios/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userExistente)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("OK"))
            .andExpect(jsonPath("$.nombre").value("Camilo"))
            .andExpect(jsonPath("$.email").value("camilo@mail.com"))
            .andExpect(jsonPath("$.password").value("1234"));
    }

    //Test para simular el login de un usuario no registrado

    @Test
    void loginUsuario_ReturnError() throws Exception {
        usuario usuarioInexistente = new usuario();
        usuarioInexistente.setEmail("noexiste@gmail.com");
        usuarioInexistente.setPassword("1234");

        //Simular el comportamiento del metodo autenticar con un usuario no registrado

        when(usuarioService.autenticar("noexiste@gmail.com", "1234"))
            .thenReturn(Optional.empty());

        //Simular la peticicion POST del usuarioController para iniciar sesion por un usuario no registrado

        mockMvc.perform(post("/api/v1/usuarios/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(usuarioInexistente)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("result", "ERROR"));
    }
}