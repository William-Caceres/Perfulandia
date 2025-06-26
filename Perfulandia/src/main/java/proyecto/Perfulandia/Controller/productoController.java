package proyecto.Perfulandia.Controller;

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

@RestController
@RequestMapping("/api/v1/productos")

@Tag(name = "Productos",
description = "Este archivo se encarga de GUARDAR, LISTAR, MODIFICAR, ELIMINAR productos en el sistema")
public class productoController {
    
    @Autowired
    private productoService productoService;

        // Listar productos
    @Operation(summary = "Listar productos", 
    description = "Lista todos los productos que se encuentren registrados en el sistema")

    @GetMapping("/listar")
    public List<producto> listarProductos(){
        return productoService.getAllProductos();
    }

    // Agregar un producto
    @Operation(summary = "Crear un producto", 
    description = "Cre un producto utilizando los datos que el usuario ingrese")

    @PostMapping("/crear")
    public producto agregarProducto(@RequestBody producto producto){
        return productoService.saveProducto(producto);
    }

    // Buscar por id
    @Operation(summary = "Buscar producto por su ID", 
    description = "Busca un producto singular segun su ID y lo retorna para distintos procesos,"+
    " como por ejemplo, para eliminar o modificar este mismo")

    @GetMapping("/buscar/{id}")
        public producto buscarProducto(@PathVariable int id){
            return productoService.getSingleProduct(id);
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
