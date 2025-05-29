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

    //Metodo para crear una notificacion
    public Notificación crearNotificacion(Notificación notificacion) {
    return notificacionRepository.save(notificacion);
}

 // Método para obtener todas las notificaciones del destinatario conectado:
    public List<Notificación> get_notificaciones_destinatario(String destinatario) {
        return notificacionRepository.findByDestinatario(destinatario);
    }
}
