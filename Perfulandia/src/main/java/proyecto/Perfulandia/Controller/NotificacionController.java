package proyecto.Perfulandia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.Perfulandia.Model.Notificacion;
import proyecto.Perfulandia.Service.NotificacionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v2/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionService notificacionService;
    // Método que se usará para obtener todas las notificaciones existentes del sistema
    @GetMapping
    public List<Notificacion> listar_notificaciones() {
        return notificacionService.getAllNotificaciones();
    }
    // agregar endpoint para notificaciones
    @PostMapping("/crear")
    public Notificacion crearNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.saveNotificacion(notificacion);
    }

    // Método para obtener todas las notificaciones del destinatario conectado:
    @GetMapping("/destinatario/{destinatario}")
    public List<Notificacion> notificacionesPorUsuario(@PathVariable String destinatario) {
        return notificacionService.getNotificacionesDestinatario(destinatario);
    }

    // Metodo para eliminar las notificaciones del usuario conectado SIN USAR
    /*
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable int id) {notificacionService.eliminarNotificacion(id);
    return ResponseEntity.noContent().build();}
    */
}
