package proyecto.Perfulandia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.Perfulandia.Model.Notificacion;
import proyecto.Perfulandia.Repository.NotificacionRepository;

@Service
public class NotificacionService {
    @Autowired
    private NotificacionRepository notificacionRepository;
    // Método para obtener todas las notificaciones del sistema:
    public List<Notificacion> getAllNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Notificacion getSingleNotificacion(int id) {
        return notificacionRepository.findById(id);
    }

    //Metodo para crear una notificacion
    public Notificacion saveNotificacion(Notificacion notificacion) {
    return notificacionRepository.save(notificacion);
}

 // Método para obtener todas las notificaciones del destinatario conectado:
    public List<Notificacion> getNotificacionesDestinatario(String destinatario) {
        return notificacionRepository.findByDestinatario(destinatario);
    }

    public void eliminarNotificacion(int id) {
        // Creacion Metodo para eliminar notificacion
        notificacionRepository.deleteById(id);
    }
}