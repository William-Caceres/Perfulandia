package proyecto.Perfulandia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.Perfulandia.Model.Incidencia;

import proyecto.Perfulandia.Service.IncidenciaService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v2/incidencia")
public class IncidenciaController {
    
    @Autowired
    private IncidenciaService inciServ;
    
    //LISTAR
    @GetMapping
    public List<Incidencia> listarIncidencias() {
        return inciServ.getIncidencias();
    }
    
    //GUARDAR
    @PostMapping
    public Incidencia agregarIncidencia(@RequestBody Incidencia incidencia){
        return inciServ.saveIncidencia(incidencia);
    }
    
}
