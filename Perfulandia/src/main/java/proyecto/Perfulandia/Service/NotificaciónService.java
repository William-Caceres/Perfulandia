package proyecto.Perfulandia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.Perfulandia.Model.Notificación;
import proyecto.Perfulandia.Repository.NotificaciónRepository;

@Service
public class NotificaciónService {
    @Autowired
    private NotificaciónRepository notificacionRepository;
    // Método para obtener todas las notificaciones del sistema:
    public List<Notificación> get_notificaciones() {
        return notificacionRepository.findAll();
    }
}
