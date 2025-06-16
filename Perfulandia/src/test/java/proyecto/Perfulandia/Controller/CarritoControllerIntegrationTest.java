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
    void agregarProducto_Carrito_responderConfirmacion() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);

        mockMvc.perform(post("/api/v2/carrito/agregar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto se agrego al carrito: Ejemplo"));
    }

    @Test
    void verCarrito_mostrarProductosDentro() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);
        mockMvc.perform(post("/api/v2/carrito/agregar/1"));

        mockMvc.perform(get("/api/v2/carrito/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ejemplo"));
    }

    @Test
    void eliminarProducto_Carrito_responderCorrectamente() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);
        mockMvc.perform(post("/api/v2/carrito/agregar/1"));

        mockMvc.perform(delete("/api/v2/carrito/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado del carrito"));
    }

    @Test
    void vaciarCarrito_responderCorrectamente() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);
        mockMvc.perform(post("/api/v2/carrito/agregar/1"));

        mockMvc.perform(delete("/api/v2/carrito/vaciar"))
                .andExpect(status().isOk())
                .andExpect(content().string("El carrito se ha vaciado"));
    }

    @Test
    void totalLibrosCarrito_debeRetornarCantidad() throws Exception {
        when(prodService.getSingleProduct(1)).thenReturn(prodEjemplo);
        mockMvc.perform(post("/api/v2/carrito/agregar/1"));

        mockMvc.perform(get("/api/v2/carrito/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}