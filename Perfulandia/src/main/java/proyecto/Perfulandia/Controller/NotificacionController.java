package proyecto.Perfulandia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.Perfulandia.Model.Notificación;
import proyecto.Perfulandia.Service.NotificaciónService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v2/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificaciónService notificaciónService;
    // Método que se usará para obtener todas las notificaciones existentes del sistema
    @GetMapping
    public List<Notificación> listar_notificaciones() {
        return notificaciónService.get_notificaciones();
    }
    // agregar endpoint para notificaciones
    @PostMapping("/crear")
    public Notificación crearNotificación(@RequestBody Notificación notificación) {return notificaciónService.crearNotificacion(notificación);
    }

    // Método para obtener todas las notificaciones del destinatario conectado:
    @GetMapping("/destinatario/{destinatario}")
    public List<Notificación> notificacionesPorUsuario(@PathVariable String destinatario) {
        return notificaciónService.get_notificaciones_destinatario(destinatario);
    }

    // Metodo para eliminar las notificaciones del usuario conectado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable int id) {notificaciónService.eliminarNotificacion(id);
    return ResponseEntity.noContent().build();}
    
}
