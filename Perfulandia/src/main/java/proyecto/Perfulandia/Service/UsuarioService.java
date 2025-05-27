package proyecto.Perfulandia.Service;

// IMPORTS para usuarioSERVICE
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.Perfulandia.Model.usuario;
import proyecto.Perfulandia.Repository.UsuarioRepository;
// codigo de usuarioSERVICE
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;
        
    public usuario registrar(usuario user){
        return repo.save(user);
    }

    public Optional<usuario> autenticar(String email, String password){
        return repo.findByEmail(email).filter(user -> user.getPassword().equals(password));
    }

}
