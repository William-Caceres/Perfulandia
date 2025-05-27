package proyecto.Perfulandia.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.Perfulandia.Model.usuario;

public interface UsuarioRepository extends JpaRepository<usuario, Long>{
    Optional<usuario> findByEmail(String email);
}