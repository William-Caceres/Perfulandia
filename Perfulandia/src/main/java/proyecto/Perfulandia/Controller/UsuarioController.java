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

import proyecto.Perfulandia.Model.usuario;
import proyecto.Perfulandia.Service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin
public class UsuarioController {
    
    @Autowired
    private UsuarioService serv;

    @PostMapping("/registrar")
        public usuario registrar(@RequestBody usuario u) {
            return serv.registrar(u);
        }

    @PostMapping("/login")
        public Map<String, String> login(@RequestBody usuario u) {
            Optional<usuario> user = serv.autenticar(u.getEmail(), u.getPassword());
            Map<String, String > respuesta = new HashMap<>();

            if (user.isPresent()) {
                respuesta.put("result", "OK");
                respuesta.put("nombre", user.get().getNombre());
            }
            else{
                respuesta.put("result", "ERROR");
            }
            return respuesta;
        }
}
