package proyecto.Perfulandia.assemblers;

import proyecto.Perfulandia.Model.Notificacion;
import proyecto.Perfulandia.Controller.NotificacionControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component

public class NotificacionModelAssembler implements 
RepresentationModelAssembler<Notificacion, EntityModel<Notificacion>> {
    
    @Override
    public @NonNull EntityModel<Notificacion> toModel(Notificacion noti) {
        return EntityModel.of(noti,
        linkTo(methodOn(NotificacionControllerV2.class)
        .buscarNotificacion(noti.getId())).withRel("buscar"),
        linkTo(methodOn(NotificacionControllerV2.class)
        .listar_notificaciones()).withRel("listar"),
        linkTo(methodOn(NotificacionControllerV2.class)
        .notificacionesPorUsuario(noti.getDestinatario())).withRel("destinatario"),
         linkTo(methodOn(NotificacionControllerV2.class)
        .eliminarNotificacion(noti.getId())).withRel("eliminar")

        );
    }
}
