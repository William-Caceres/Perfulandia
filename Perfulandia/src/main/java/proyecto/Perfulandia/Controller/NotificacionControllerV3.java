package proyecto.Perfulandia.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.Perfulandia.Model.Notificacion;
import proyecto.Perfulandia.Service.NotificacionService;
import proyecto.Perfulandia.assemblers.NotificacionModelAssembler;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.media.MediaType;

@RestController
@RequestMapping("/api/v3/notificaciones")

@Tag(name = "Notificaciones",
description = "Genera notificaciones (de momento solo una), sobre las distintas acciones que se realizan en la pagina")
public class NotificacionControllerV3 {

    @Autowired
    private NotificacionService notificacionService;

    @Autowired
    private NotificacionModelAssembler assembler;

    // Método que se usará para obtener todas las notificaciones existentes del sistema
    @Operation(summary = "Listar notificaciones",
    description = "Lista todas las notificaciones que estan registradas en el sistema")

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Notificacion>> listar_notificaciones() {
        List<EntityModel<Notificacion>> notificaciones = notificacionService.getAllNotificaciones().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel
            .of(notificaciones, linkTo(methodOn(NotificacionControllerV3.class)
            .listar_notificaciones()).withSelfRel());
    }

    //Buscar noti
    @Operation(summary = "Buscar notificacion singular",
    description = "Este metodo busca una notificacion singular segun la ID que le entreguemos")

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Notificacion> buscarNotificacion(@PathVariable int id) {
        Notificacion noti = notificacionService.getSingleNotificacion(id);
        return assembler.toModel(noti);
    }

    // agregar endpoint para notificaciones
    @Operation(summary = "Generar notificacion",
    description = "Crea una notificacion en base a una accion que se haya realizado en la pagina,"+
    " como el concretar una compra")

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Notificacion>> crearNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion crear = notificacionService.saveNotificacion(notificacion);
        return ResponseEntity
            .created(linkTo(methodOn(NotificacionControllerV3.class)
            .buscarNotificacion(crear.getId())).toUri()).body(assembler.toModel(crear));
    }

    // Método para obtener todas las notificaciones del destinatario conectado:
    @Operation(summary = "Notificar a usuario especifico",
    description = "Lista todas las notificaciones que van dirigidas a un usuario segun el "+
    "destinatario que le entreguemos (solo el las puede ver)")

    @GetMapping(value = "/{destinatario}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Notificacion>> notificacionesPorUsuario(@PathVariable String destinatario) {
        List<EntityModel<Notificacion>> notiDesti = notificacionService.getNotificacionesDestinatario(destinatario).stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel
            .of(notiDesti, linkTo(methodOn(NotificacionControllerV3.class)
            .notificacionesPorUsuario(destinatario)).withSelfRel());
    }

    // Metodo para eliminar las notificaciones del usuario conectado SIN USAR
    /*
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable int id) {notificacionService.eliminarNotificacion(id);
    return ResponseEntity.noContent().build();}
    */
}
