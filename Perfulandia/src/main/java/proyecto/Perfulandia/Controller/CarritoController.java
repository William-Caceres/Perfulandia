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
        producto Producto = Productoserv.getProducto(id);
        if (Producto != null) {
            carrito.add(Producto);
            return "Producto se agrego al carrito: " + Producto.getNombre();
        }
        return "El Producto no fue encontrado";
    }
    
    //Metodo para eliminar Producto del carrito
    @DeleteMapping("/eliminar/{id}") 
    public String eliminarProducto(@PathVariable int id) {
        boolean eliminado = carrito.removeIf(producto -> producto.getId() == id);
        return eliminado ? "Producto ha sido eliminado" : "Producto no encontrado";
    }
    
    //Metodo para ver el carrito
    @GetMapping
    public List<producto> verCarrito() {
        return carrito;
    }

    //Metodo para vaciar el carrito de compras
    @DeleteMapping("/vaciar")
    public String vaciarCarrito(){
        carrito.clear();
        return "El carrito se ha vaciado";
    }
    
    //Metodo para contar los items del carrito
    @GetMapping("/total")
    public int totalLibrosCarrito() {
        return carrito.size();
    }
}
