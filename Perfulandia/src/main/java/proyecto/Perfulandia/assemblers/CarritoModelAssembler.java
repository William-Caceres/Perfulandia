package proyecto.Perfulandia.assemblers;

import proyecto.Perfulandia.Model.producto;
import proyecto.Perfulandia.Controller.CarritoControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component

public class CarritoModelAssembler implements 
RepresentationModelAssembler<producto, EntityModel<producto>> {
    
    @Override
    public @NonNull EntityModel<producto> toModel(producto prod) {
        return EntityModel.of(prod,
        linkTo(methodOn(CarritoControllerV2.class)
        .verCarrito()).withRel("listar"),
        linkTo(methodOn(CarritoControllerV2.class)
        .eliminarProducto(prod.getId())).withRel("eliminar")
        );
    }
}
