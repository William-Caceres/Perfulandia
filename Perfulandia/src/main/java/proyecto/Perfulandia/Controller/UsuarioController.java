package proyecto.Perfulandia.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import proyecto.Perfulandia.Model.usuario;
import proyecto.Perfulandia.Service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin

@Tag(name = "Usuarios y autentificacion",
description = "Este controlador permite que un usuario se registre (guardar en la BDD) e inicie sesion")
public class UsuarioController {
    
    @Autowired
    private UsuarioService serv;

    @Operation(summary = "Registrar usuario", 
    description = "Registra un usuario en el sistema, es decir, lo guarda en la base de datos "+
    "permitiendo a este que pueda iniciar sesion y que el sistema lo recuerde")
    @PostMapping("/registrar")
        public usuario registrar(@RequestBody usuario u) {
            return serv.registrar(u);
        }

    @Operation(summary = "Iniciar sesion", 
    description = "Permite a un usuario que se haya registrado en la base de datos, iniciar sesion "+
    "con sus credenciales")
    @PostMapping("/login")
        public Map<String, String> login(@RequestBody usuario u) {
            Optional<usuario> user = serv.autenticar(u.getEmail(), u.getPassword());
            Map<String, String > respuesta = new HashMap<>();

            if (user.isPresent()) {
                respuesta.put("result", "OK");
                respuesta.put("nombre", user.get().getNombre());
                //solo para el test 
                respuesta.put("email", user.get().getEmail());
                respuesta.put("password", user.get().getPassword());
            }
            else{
                respuesta.put("result", "ERROR");
            }
            return respuesta;
        }
}
