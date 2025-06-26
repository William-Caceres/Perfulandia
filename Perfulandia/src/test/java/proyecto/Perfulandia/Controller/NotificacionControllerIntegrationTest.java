package proyecto.Perfulandia.Controller;

import proyecto.Perfulandia.Model.Notificacion;
import proyecto.Perfulandia.Service.NotificacionService;


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

@WebMvcTest(NotificacionController.class)

public class NotificacionControllerIntegrationTest {
    
 @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionService notService;

    @Autowired
    private ObjectMapper objectMapper;

    /*
    @Test
    void listarNotificaciones_retornarListaJson() throws Exception {
        List<Notificacion> notificacion = List.of(
                new Notificacion(1,"cliente_01","Se concreto la compra 01"),
                new Notificacion(1,"cliente_02","Se concreto la compra 02"),
                new Notificacion(1,"cliente_02","Se concreto la compra 03")
        );

        when(notService.getAllNotificaciones()).thenReturn(notificacion);

        mockMvc.perform(get("/api/v1/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].nombre").value("Perfume"));
    }
    */

    @Test
    void crearNotificacion_retornarGuardar() throws Exception {
        Notificacion noti = new Notificacion(4,"Cliente de prueba","Mensaje de prueba");

        when(notService.saveNotificacion(any(Notificacion.class))).thenReturn(noti);

        mockMvc.perform(post("/api/v1/notificaciones/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noti)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.destinatario").value("Cliente de prueba"));
    }

    @Test
    void buscarNotificacion_porDestinatario() throws Exception {
        List<Notificacion> notificacion = List.of(
                new Notificacion(1,"cliente_01","Se concreto la compra 01"),
                new Notificacion(2,"cliente_02","Se concreto la compra 02"),
                new Notificacion(3,"cliente_02","Se concreto la compra 03")
        );
        when(notService.getNotificacionesDestinatario("cliente_02")).thenReturn(notificacion);

        mockMvc.perform(get("/api/v1/notificaciones/destinatario/cliente_02"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[1].mensaje").value("Se concreto la compra 02"));
    }

    @Test
    void buscarNotificacion_porId_existente() throws Exception {
        Notificacion noti = new Notificacion(1,"usuario", "mensaje evniado al usuario");

        when(notService.deleteNotificacion(1)).thenReturn("OK");

        mockMvc.perform(delete("/api/v1/notificaciones/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    void eliminarNotificacion_responderCorrectamente() throws Exception {
        Notificacion noti = new Notificacion(1, "usuario", "mensaje eliminar");

        when(notService.getSingleNotificacion(1)).thenReturn(noti);

    }

}
