package proyecto.Perfulandia.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.Perfulandia.Model.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    Notificacion findById(int id);
    List<Notificacion> findByDestinatario(String destinatario);
}