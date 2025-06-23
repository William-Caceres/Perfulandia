package proyecto.Perfulandia.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.Perfulandia.Model.producto;
import proyecto.Perfulandia.Service.productoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//Importar el assembler para HATEOAS de este controller
import proyecto.Perfulandia.assemblers.productoModelAssembler;

//Importar las clases necesarias para usar HATEOAS
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//Importar las clases de HATEOAS EntityModel, CollectionModel y MediaType para manejar los modelos de respuestas
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;

//Importar las clases responseEntity para manejar las respuestas HTTP
import org.springframework.http.ResponseEntity;

//Importar Stream y cocciones para manejar la lista de productos en java
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v3/productos")

@Tag(name = "Productos",
description = "Este archivo se encarga de GUARDAR, LISTAR, MODIFICAR, ELIMINAR productos en el sistema")
public class productoControllerV3 {
    
    @Autowired
    private productoService productoService;

    @Autowired
    private productoModelAssembler assembler;

    // Listar productos
    @Operation(summary = "Listar productos", 
    description = "Lista todos los productos que se encuentren registrados en el sistema")

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<producto>> listarProductos(){
        //Obtener la lista de libros y la convertimos a EntityModel
        List<EntityModel<producto>> productos = productoService.getAllProductos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel
            .of(productos, linkTo(methodOn(productoControllerV3.class)
            .listarProductos()).withSelfRel());
    }

    // Agregar un producto
    @Operation(summary = "Crear un producto", 
    description = "Cre un producto utilizando los datos que el usuario ingrese")

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<producto>> agregarProducto(@RequestBody producto producto){
        producto crear = productoService.saveProducto(producto);
        return ResponseEntity
            .created(linkTo(methodOn(productoControllerV3.class)
            .buscarProducto(crear.getId())).toUri()).body(assembler.toModel(crear));
    }

    // Buscar por id
    @Operation(summary = "Buscar producto por su ID", 
    description = "Busca un producto singular segun su ID y lo retorna para distintos procesos,"+
    " como por ejemplo, para eliminar o modificar este mismo")
    
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<producto> buscarProducto(@PathVariable int id){
        producto prod = productoService.getSingleProduct(id);
        return assembler.toModel(prod);
    }


    /*

    Metodos en cana

    @PutMapping("/actualizar/{id}")
    // Se busca por ID y se actualiza un producto
    public producto actualizarProducto(@PathVariable int id, @RequestBody producto perf){
        return productoService.updateProducto(perf);
    }

    @DeleteMapping("/eliminar/{id}")
    // Borrar producto por ID
    public String eliminarProducto(@PathVariable int id){
        return productoService.deleteProducto(id);
    }
    */

    // Mostrar el total
    @Operation(summary = "Total de productos", 
    description = "Devuelve el total de productos (en numeros) que se encuentren en el sistema")

    @GetMapping("/total")
    public int totalProductos(){
        return productoService.totalProductos();
    }


}
