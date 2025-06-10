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
    private productoService ProductoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProductos_debeRetornarListaJson() throws Exception {
        List<producto> productos = List.of(
                new producto(1,1000,"producto","marca","modelo","PaisOrigen",100,30,"kfdsjf"),
                new producto(2,2000,"producto2","marca2","modelo2","PaisOrigen2",100,30,"kfdsjf2")
        );

        when(productoService.getLibros()).thenReturn(productos);

        mockMvc.perform(get("/api/v1/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("Libro1"));
    }

    @Test
    void agregarLibro_debeGuardarYRetornarLibro() throws Exception {
        Libro libro = new Libro(0, "789", "Nuevo Libro", "Nueva Editorial", 2022, "Nuevo Autor", 12000);

        when(libroService.saveLibro(any(Libro.class))).thenReturn(libro);

        mockMvc.perform(post("/api/v1/libros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(libro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Nuevo Libro"));
    }

    @Test
    void buscarLibro_porId_existente() throws Exception {
        Libro libro = new Libro(5, "555", "Buscado", "Ed", 2021, "Autor", 18000);

        when(libroService.getLibroId(5)).thenReturn(libro);

        mockMvc.perform(get("/api/v1/libros/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Buscado"));
    }

    @Test
    void eliminarLibro_existente() throws Exception {
        when(libroService.deleteLibro(3)).thenReturn("producto eliminado");

        mockMvc.perform(delete("/api/v1/libros/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("producto eliminado"));
    }

    @Test
    void totalLibrosV2_debeRetornarCantidad() throws Exception {
        when(libroService.totalLibrosV2()).thenReturn(10);

        mockMvc.perform(get("/api/v1/libros/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

}
