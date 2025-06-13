package proyecto.Perfulandia.Controller;

//importar las clases del packend
import proyecto.Perfulandia.Model.producto;
import proyecto.Perfulandia.Service.productoService;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(productoController.class)

public class productoControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private productoService ProductoServ;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProductos_debeRetornarListaJson() throws Exception {
        List<producto> productos = List.of(
                new producto(1,1500,"Perfume","Marca Roja","Cuadrado","Francia",100,30,"imagen1.jpg"),
                new producto(2,3000,"Crema","Marca Verde","Rectangular","Italia",80,35,"imagen2.jpg"),
                new producto(3,5000,"Jabon","Marca Morada","Circular","USA",95,50,"imagen3.jpg")
        );

        when(ProductoServ.getAllProductos()).thenReturn(productos);

        mockMvc.perform(get("/api/v2/productos/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].nombre").value("Perfume"));
    }

    @Test
    void agregarProducto_debeGuardarYRetornarProducto() throws Exception {
        producto prod = new producto(4,3000,"Crema2","Marca Morada","Rectangular","USA",50,50,"imagen4.jpg");

        when(ProductoServ.saveProducto(any(producto.class))).thenReturn(prod);

        mockMvc.perform(post("/api/v2/productos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prod)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Crema2"));
    }

    @Test
    void buscarProducto_porId_existente() throws Exception {
        producto prod = new producto(5,3000,"Producto 05","Marca Morada","Rectangular","USA",50,50,"imagen4.jpg");

        when(ProductoServ.getSingleProduct(5)).thenReturn(prod);

        mockMvc.perform(get("/api/v2/productos/buscar/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto 05"));
    }

    /*
    @Test
    void eliminarProducto_existente() throws Exception {

        producto productoDT = new producto(5,3000,"ProductoDT","MarcaDT","ModeloDT","PaisDT",50,50,"imagenDT.jpg");
        when(ProductoServ.deleteProducto(productoDT)).thenReturn("Producto eliminado");

        mockMvc.perform(delete("/api/v2/productos/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado"));
    }
    */

    @Test
    void totalProductos_debeRetornarCantidad() throws Exception {
        when(ProductoServ.totalProductos()).thenReturn(10);

        mockMvc.perform(get("/api/v2/productos/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

}
