package proyecto.Perfulandia.assemblers;

import proyecto.Perfulandia.Model.Incidencia;
import proyecto.Perfulandia.Controller.IncidenciaControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component

public class IncidenciaModelAssembler implements 
RepresentationModelAssembler<Incidencia, EntityModel<Incidencia>> {
    
    @Override
    public @NonNull EntityModel<Incidencia> toModel(Incidencia inci) {
        return EntityModel.of(inci,
        linkTo(methodOn(IncidenciaControllerV2.class)
        .buscarIncidencia(inci.getId())).withRel("buscar"),
        linkTo(methodOn(IncidenciaControllerV2.class)
        .listarIncidencias()).withRel("listar")
        );
    }
}
