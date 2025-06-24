package proyecto.Perfulandia.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import proyecto.Perfulandia.Model.Incidencia;
import proyecto.Perfulandia.Service.IncidenciaService;
import proyecto.Perfulandia.assemblers.IncidenciaModelAssembler;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v3/incidencia")

@Tag(name = "Incidencias",
description = "Este controlador permite al usuario ingresar una incidencia/opinion sobre el sistema, a traves de un formulario")
public class IncidenciaControllerV3 {
    
    @Autowired
    private IncidenciaService inciServ;

    @Autowired
    private IncidenciaModelAssembler assembler;
    
    //LISTAR
    @Operation(summary = "Listar incidencias",
    description = "Lista todas las incidencias que se han creado por los usuarios y que esten guardadas en la BDD "+
    "despues las retorna a la pantalla")

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Incidencia>> listarIncidencias() {
        
        List<EntityModel<Incidencia>> incidencias = inciServ.getAllIncidencias().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel
            .of(incidencias, linkTo(methodOn(IncidenciaControllerV3.class)
            .listarIncidencias()).withSelfRel());
    }
    
    @Operation(summary = "Buscar una Incidencia",
    description = "Este metodo busca una incidencia en especifico segun la ID que le entreguemos")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
        public EntityModel<Incidencia> buscarIncidencia(@PathVariable int id) {
            Incidencia inci = inciServ.getSingleIncidencia(id);
            return assembler.toModel(inci);
        }

    //GUARDAR
    @Operation(summary = "Crear incidencia",
    description = "Crea una incidencia en base a los datos ingresados por el usuario, se guarda en la BDD") 

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Incidencia>> agregarIncidencia(@RequestBody Incidencia incidencia){
        Incidencia crear = inciServ.saveIncidencia(incidencia);
        return ResponseEntity
            .created(linkTo(methodOn(IncidenciaControllerV3.class)
            .buscarIncidencia(crear.getId())).toUri()).body(assembler.toModel(crear));
    }
}
