package proyecto.Perfulandia.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.Perfulandia.Model.Notificación;

@Repository
public interface NotificaciónRepository extends JpaRepository<Notificación, Integer> {
    Optional<Notificación> findById(int id);
    List<Notificación> findByDestinatario(String destinatario);
}