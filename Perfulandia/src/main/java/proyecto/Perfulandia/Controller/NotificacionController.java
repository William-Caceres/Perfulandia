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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/notificaciones")

@Tag(name = "Notificaciones",
description = "Genera notificaciones (de momento solo una), sobre las distintas acciones que se realizan en la pagina")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;
    // Método que se usará para obtener todas las notificaciones existentes del sistema
    @Operation(summary = "Listar notificaciones",
    description = "Lista todas las notificaciones que estan registradas en el sistema")

    @GetMapping("/listar")
    public List<Notificacion> listar_notificaciones() {
        return notificacionService.getAllNotificaciones();
    }

    @Operation(summary = "Buscar notificacion singular",
    description = "Este metodo busca una notificacion singular segun la ID que le entreguemos")

    @GetMapping("/buscar/{id}")
    public Notificacion buscarNotificacion(@PathVariable int id) {
        return notificacionService.getSingleNotificacion(id);
    }
    
    // agregar endpoint para notificaciones
    @Operation(summary = "Generar notificacion",
    description = "Crea una notificacion en base a una accion que se haya realizado en la pagina,"+
    " como el concretar una compra")

    @PostMapping("/crear")
    public Notificacion crearNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.saveNotificacion(notificacion);
    }

    // Método para obtener todas las notificaciones del destinatario conectado:
    @Operation(summary = "Notificar a usuario especifico",
    description = "Lista todas las notificaciones que van dirigidas a un usuario segun el "+
    "destinatario que le entreguemos (solo el las puede ver)")

    @GetMapping("/destinatario/{destinatario}")
    public List<Notificacion> notificacionesPorUsuario(@PathVariable String destinatario) {
        return notificacionService.getNotificacionesDestinatario(destinatario);
    }

    @Operation(summary = "Eliminar notificacion",
    description = "Elimina una notificacion segun su id")

    @DeleteMapping("/eliminar/{id}")
    public String eliminarNotificacion(@PathVariable int id) {
        return notificacionService.deleteNotificacion(id);
    }
}
