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

import proyecto.Perfulandia.Model.PerfumeModel;
import proyecto.Perfulandia.Service.PerfumeService;



@RestController
@RequestMapping("/api/v2/carrito")
public class CarritoController {
    private final List<PerfumeModel> carrito = new ArrayList<>();

    @Autowired
    private PerfumeService Perfumeserv;

    //agregar Perfume al carrito
    @PostMapping("/agregar/{id}")
    public String agregarPerfume(@PathVariable int id) {
        PerfumeModel Perfume = Perfumeserv.getPerfume(id);
        if (Perfume != null) {
            carrito.add(Perfume);
            return "Perfume se agrego al carrito: " + Perfume.getNombre();
        }
        return "El Perfume no fue encontrado";
    }
    
    //Metodo para eliminar Perfume del carrito
    @DeleteMapping("/eliminar/{id}") 
    public String eliminarPerfume(@PathVariable int id) {
        boolean eliminado = carrito.removeIf(Perfume -> Perfume.getId() == id);
        return eliminado ? "Perfume ha sido eliminado" : "Perfume no encontrado";
    }
    
    //Metodo para ver el carrito
    @GetMapping
    public List<PerfumeModel> verCarrito() {
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
