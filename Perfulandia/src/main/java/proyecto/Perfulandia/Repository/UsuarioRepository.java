package proyecto.Perfulandia.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.Perfulandia.Model.UsuarioModel;

public class UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    Optional<UsuarioModel> findByEmail(String email);
}
