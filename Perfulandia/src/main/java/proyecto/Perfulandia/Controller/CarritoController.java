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



@RestController
@RequestMapping("/api/v2/carrito")
public class CarritoController {
    private final List<producto> carrito = new ArrayList<>();

    @Autowired
    private productoService Productoserv;

    //agregar Producto al carrito
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
    @DeleteMapping("/eliminar/{id}") 
    public String eliminarProducto(@PathVariable int id) {
        producto Producto = Productoserv.getSingleProduct(id);
        int reStock = Producto.getStock()+1;
        for (producto prod : carrito) {
            if (prod.getId() == id) {
                Productoserv.updateProducto(id, reStock);
                carrito.removeFirst();
                break;
            }
        }
        return "Producto no encontrado";
    }

    //Metodo para vaciar el carrito de compras
    @DeleteMapping("/vaciar")
    public String vaciarCarrito(){
        for (producto producto : carrito) {
            int id = producto.getId();
            int reStock = producto.getStock()+1;
            Productoserv.updateProducto(id, reStock);
        }
        
        carrito.clear();
        return "El carrito se ha vaciado";
    }

    //Metodo para ver el carrito
    @GetMapping
    public List<producto> verCarrito() {
        return carrito;
    }
    
    //Metodo para contar los items del carrito
    @GetMapping("/total")
    public int totalLibrosCarrito() {
        return carrito.size();
    }
}
