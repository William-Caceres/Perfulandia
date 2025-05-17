package com.example.bibliotecaduocV2.Controller;

import com.example.bibliotecaduocV2.Model.Libro;
import com.example.bibliotecaduocV2.Service.LibroService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/v2/carrito")
public class CarritoController {
    private final List<Libro> carrito = new ArrayList<>();

    @Autowired
    private LibroService libroserv;

    //agregar libro al carrito
    @PostMapping("/agregar/{id}")
    public String agregarLibro(@PathVariable int id) {
        Libro libro = libroserv.getLibroId(id);
        if (libro != null) {
            carrito.add(libro);
            return "Libro se agrego al carrito: " + libro.getTitulo();
        }
        return "El libro no fue encontrado";
    }
    
    //Metodo para eliminar libro del carrito
    @DeleteMapping("/eliminar/{id}") 
    public String eliminarLibro(@PathVariable int id) {
        boolean eliminado = carrito.removeIf(libro -> libro.getId() == id);
        return eliminado ? "Libro ha sido eliminado" : "Libro no encontrado";
    }
    
    //Metodo para ver el carrito
    @GetMapping
    public List<Libro> verCarrito() {
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
