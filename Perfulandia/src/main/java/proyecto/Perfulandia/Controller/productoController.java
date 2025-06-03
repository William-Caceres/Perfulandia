package proyecto.Perfulandia.Controller;

import java.util.*;
import java.util.Optional;

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



@RestController
@RequestMapping("/api/v2/productos")
public class productoController {
    
    @Autowired
    private productoService productoService;

    @GetMapping("/listar")
    // Listar productos
    public List<producto> listarProductos(){
        return productoService.getAllProductos();
    }

    @PostMapping("/crear")
    // Agregar un producto
    public producto agregarProducto(@RequestBody producto producto){
        return productoService.saveProducto(producto);
    }

    @GetMapping("/buscar/{id}")
    // Buscar por id
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

    @GetMapping("/total")
    // Mostrar el total
    public int totalProductosV2(){
        int totalPrd = 0;
        for (producto prod : productoService.getAllProductos()) {
            totalPrd = totalPrd + 1;
        }
        return totalPrd;
    }


}
