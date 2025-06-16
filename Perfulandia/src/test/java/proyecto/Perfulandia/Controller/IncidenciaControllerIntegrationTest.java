package proyecto.Perfulandia.Controller;

//importar las clases del packend
import proyecto.Perfulandia.Model.Incidencia;
import proyecto.Perfulandia.Service.IncidenciaService;


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

@WebMvcTest(IncidenciaController.class)

public class IncidenciaControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidenciaService inciServ;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProductos_debeRetornarListaJson() throws Exception {
        List<Incidencia> incidencias = List.of(
                new Incidencia(1,"Puntilio","Fue una buena experiancia","El color en general no me agrado",8),
                new Incidencia(2,"San Rotus","Fue corta mi experiancia, no recuerdo mucho","Ojala siguiera ahi",6),
                new Incidencia(3,"Agriculis","Talves el lugar que mas extraño","Talves evitar los conflictos",10)
        );

        when(inciServ.getIncidencias()).thenReturn(incidencias);

        mockMvc.perform(get("/api/v2/incidencia/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].nombre_opinion").value("Puntilio"));
    }

    @Test
    void agregarProducto_debeGuardarYRetornarProducto() throws Exception {
        Incidencia inci = new Incidencia(4,"San Juan","No recuerdo nada sobre este lugar, odio eso","Sigue existiendo ese resfalin?",1);

        when(inciServ.saveIncidencia(any(Incidencia.class))).thenReturn(inci);

        mockMvc.perform(post("/api/v2/incidencia/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inci)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre_opinion").value("San Juan"));
    }
}
