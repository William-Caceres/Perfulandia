package proyecto.Perfulandia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.Perfulandia.Model.PerfumeModel;
import proyecto.Perfulandia.Service.PerfumeService;



@RestController
@RequestMapping("/api/v2/perfumes")
public class PerfumeController {
    
    @Autowired
    private PerfumeService perfumeService;

    @GetMapping
    // Listar perfumes
    public List<PerfumeModel> listarPerfumes(){
        return perfumeService.getPerfumeModels();
    }

    @PostMapping
    // Agregar un perfume
    public PerfumeModel agregarPerfume(@RequestBody PerfumeModel perfume){
        return perfumeService.savePerfume(perfume);
    }

    @GetMapping("{id}")
    // Buscar por id
        public PerfumeModel buscarPerfume(@PathVariable int id){
            return perfumeService.getPerfume(id);
        }

    @PutMapping("{id}")
    // Se busca por ID y se actualiza un perfume
    public PerfumeModel actualizarPerfume(@PathVariable int id, @RequestBody PerfumeModel perf){
        return perfumeService.updatePerfume(perf);
    }

    @DeleteMapping("{id}")
    // Borrar perfume por ID
    public String eliminarPerfume(@PathVariable int id){
        return perfumeService.deletePerfume(id);
    }
    
    @GetMapping("/total")
    // Mostrar el total
    public int totalPerfumesV2(){
        return perfumeService.totalPerfumesV2();
    }

}
