package proyecto.Perfulandia.assemblers;

import proyecto.Perfulandia.Model.usuario;
import proyecto.Perfulandia.Controller.UsuarioControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component

public class UsuarioModelAssembler implements 
RepresentationModelAssembler<usuario, EntityModel<usuario>> {
    @Override
    public @NonNull EntityModel<usuario> toModel(usuario user) {
        return EntityModel.of(user,
        linkTo(methodOn(UsuarioControllerV2.class).registrar(null))
        .withRel("registrar"),
        linkTo(methodOn(UsuarioControllerV2.class).login(user))
        .withRel("login")
        );
    }
}
