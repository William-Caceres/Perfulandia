package proyecto.Perfulandia.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.Perfulandia.Model.producto;
import proyecto.Perfulandia.Service.productoService;

//Importar las librerias de SWAGGER para la documentacion de las APIs
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/carrito")

//Agregar anotacion Tag para darle nombre y descripcion a la API
@Tag(name = "Carrto de compras y gestion de STOCK",
description = "Operaciones sobre el carrito de compras")
public class CarritoController {
    private final List<producto> carrito = new ArrayList<>();

    @Autowired
    private productoService Productoserv;

    //agregar Producto al carrito
    //Agregar anotacion Operation para documentar cada endpoint o metodo REST
    @Operation(summary = "Agrega un producto al Carrito de compras", 
    description = "Este metodo agrega un producto al carrito de compras, por su id,"+
    " verificando que el producto exista, en conjunto a esto, se le resta al stock "+
    "del producto una unidad, esto para la gestion de STOCK")

    @PostMapping("/agregar/{id}")
    public String agregarProducto(@PathVariable int id) {
        producto Producto = Productoserv.getSingleProduct(id);
        if (Producto != null) {
            carrito.add(Producto);
            int newStock = Producto.getStock()-1;
            Productoserv.updateProducto(id, newStock);
            return "Producto se agrego al carrito: " + Producto.getNombre();
        }
        return "El Producto no fue encontrado";
    }
    
    //Metodo para eliminar Producto del carrito
    @Operation(summary = "Quita un producto del Carrito de compras",
    description = "Quita un producto del carrito de compras segun su id, en conjunto"+
    " a esto, al quitar un producto se le suma una unidad al stock del producto que "+
    "fue removido del Carrito")

    @DeleteMapping("/eliminar/{id}") 
    public String eliminarProducto(@PathVariable int id) {
        producto Producto = Productoserv.getSingleProduct(id);
        int reStock = Producto.getStock()+1;
        for (producto prod : carrito) {
            if (prod.getId() == id) {
                Productoserv.updateProducto(id, reStock);
                carrito.removeFirst();
                return "Producto eliminado del carrito";
            }
        }
        return "No se pudo eliminar el producto seleccionado";
    }

    //Metodo para vaciar el carrito de compras
    @Operation(summary = "Vacia por completo el carrito de compras",
    description = "Quita todos los productos del carrito de compras, tambien restaura"+
    " el stock de estos antes de que fueran agregados al carro") 

    @DeleteMapping("/vaciar")
    public String vaciarCarrito(){
        for (producto producto : carrito) {
            int id = producto.getId();
            producto prod = Productoserv.getSingleProduct(id);
            Productoserv.updateProducto(id, prod.getStock() + 1);
        }
        carrito.clear();
        return "El carrito se ha vaciado";
    }

    //Metodo para ver el carrito
    @Operation(summary = "Carga la lista de productos que estan en el carrito",
    description = "Este metodo es para recargar la lista de productos que esten en el"+
    " carrito en ese momento, sirve principalmente para refrescar los cambios que se "+
    "apliquen sobre esta") 

    @GetMapping("/listar")
    public List<producto> verCarrito() {
        return carrito;
    }
    
    //Metodo para contar los items del carrito
    @Operation(summary = "Contar el total de productos dentro del carrito",
    description = "Este metodo cuenta la cantidad de productos que estan dentro del"+
    " carrito en ese momento") 

    @GetMapping("/total")
    public int totalLibrosCarrito() {
        return carrito.size();
    }
}
