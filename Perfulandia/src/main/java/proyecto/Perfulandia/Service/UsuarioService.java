package proyecto.Perfulandia.Service;

// IMPORTS para usuarioSERVICE
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.Perfulandia.Model.UsuarioModel;
import proyecto.Perfunaldia.Repository.UsuarioRepository;

// codigo de usuarioSERVICE
@Service
public abstract class UsuarioService {
    @Autowired
    private UsuarioRepository repo;
        
    public UsuarioModel registrar(UsuarioModel user){
        return repo.save(user);
    }

    public Optional<Usuario> autenticar(String email, String password){
        return repo.findByEmail(email).filter(user -> user.getPassword().equals(password))
    }

}
