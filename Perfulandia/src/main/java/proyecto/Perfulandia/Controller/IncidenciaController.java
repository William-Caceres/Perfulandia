package proyecto.Perfulandia.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.Perfulandia.Model.Incidencia;

import proyecto.Perfulandia.Service.IncidenciaService;;

@RestController
@RequestMapping("/api/v2/incidencia")
public class IncidenciaController {
    @Autowired
    private IncidenciaService inciServ;
    //Agregar incidencia
    @PostMapping
    public Incidencia agregarIncidencia(@RequestBody Incidencia incidencia){
        return inciServ.saveIncidencia(incidencia);
    }
    
}
