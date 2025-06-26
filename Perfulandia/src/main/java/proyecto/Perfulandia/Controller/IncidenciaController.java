package proyecto.Perfulandia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import proyecto.Perfulandia.Model.Incidencia;
import proyecto.Perfulandia.Service.IncidenciaService;

@RestController
@RequestMapping("/api/v1/incidencia")

@Tag(name = "Incidencias",
description = "Este controlador permite al usuario ingresar una incidencia/opinion sobre el sistema, a traves de un formulario")
public class IncidenciaController {
    
    @Autowired
    private IncidenciaService inciServ;
    
    //LISTAR
    @Operation(summary = "Listar incidencias",
    description = "Lista todas las incidencias que se han creado por los usuarios y que esten guardadas en la BDD "+
    "despues las retorna a la pantalla")

    @GetMapping("/listar")
    public List<Incidencia> listarIncidencias() {
        return inciServ.getAllIncidencias();
    }
    
    // 
    @Operation(summary = "Buscar una Incidencia",
    description = "Este metodo busca una incidencia en especifico segun la ID que le entreguemos")
    @GetMapping("/buscar/{id}")
        public Incidencia buscarIncidencia(@PathVariable int id) {
            return inciServ.getSingleIncidencia(id);
        }

    //GUARDAR
    @Operation(summary = "Crear incidencia",
    description = "Crea una incidencia en base a los datos ingresados por el usuario, se guarda en la BDD") 

    @PostMapping("/crear")
    public Incidencia agregarIncidencia(@RequestBody Incidencia incidencia){
        return inciServ.saveIncidencia(incidencia);
    }
}
