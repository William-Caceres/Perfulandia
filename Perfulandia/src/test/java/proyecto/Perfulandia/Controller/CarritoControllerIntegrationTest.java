package proyecto.Perfulandia.Controller;


import proyecto.Perfulandia.Model.producto;
import proyecto.Perfulandia.Service.productoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// Anotación para indicar que se van a hacer pruebas de integración al controlador CarritoController
@WebMvcTest(CarritoController.class)
public class CarritoControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private productoService prodService;

    private producto prodEjemplo;

    @BeforeEach
    void setUp() {
        prodEjemplo = new producto(1,1500,"Ejemplo","Marca ejemplo","Modleo ejemplo","Pais ejemplo",100,30,"ejemplo.jpg");
    }

    @Test

    // Prueba para verificar que se puede agregar un producto al carrito
    void agregarProducto_Carrito_responderConfirmacion() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);

        mockMvc.perform(post("/api/v1/carrito/agregar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto se agrego al carrito: Ejemplo"));
    }

     // Prueba para verificar que se puede eliminar un producto del carrito
    @Test
    void eliminarProducto_Carrito_responderCorrectamente() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);
        mockMvc.perform(post("/api/v1/carrito/agregar/1"));

        mockMvc.perform(delete("/api/v1/carrito/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado del carrito"));
    }

    // Prueba para verificar que se puede vaciar el carrito correctamente
    @Test
    void vaciarCarrito_responderCorrectamente() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);
        mockMvc.perform(post("/api/v1/carrito/agregar/1"));

        mockMvc.perform(delete("/api/v1/carrito/vaciar"))
                .andExpect(status().isOk())
                .andExpect(content().string("El carrito se ha vaciado"));
    }

    //Prueba para comprobar que el carrito quede vacio despues de una compra
    @Test
    void vaciarCompra_responderCorrectamente() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);
        mockMvc.perform(post("/api/v1/carrito/agregar/1"));

        mockMvc.perform(delete("/api/v1/carrito/vcompra"))
                .andExpect(status().isOk())
                .andExpect(content().string("Carrito vaciado tras compra"));
    }

    //Prueba para mostrar los productos que estan DENTRO del carrito
    @Test
    void verCarrito_mostrarProductosDentro() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);
        mockMvc.perform(post("/api/v1/carrito/agregar/1"));

        mockMvc.perform(get("/api/v1/carrito/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ejemplo"));
    }

    //Prueba para verificar que se retorna correctamente la cantidad de productos dentro del carrito
    @Test
    void totalProductosCarrito_debeRetornarCantidad() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);
        mockMvc.perform(post("/api/v1/carrito/agregar/1"));

        mockMvc.perform(get("/api/v1/carrito/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}