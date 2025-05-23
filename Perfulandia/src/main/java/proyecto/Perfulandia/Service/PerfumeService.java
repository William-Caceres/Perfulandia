package proyecto.Perfulandia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.Perfulandia.Model.PerfumeModel;
import proyecto.Perfulandia.Repository.PerfumeRepository;

@Service
public class PerfumeService {
    @Autowired
    private PerfumeRepository perfumeRepository;

    // OBTENER/RECUPERAR TODOS LOS PERFUMES
    public List<PerfumeModel> getPerfumeModels() {
        return perfumeRepository.retornarPerfumes();
    }

    // GUARDAR PERFUME
    public PerfumeModel savePerfume(PerfumeModel perf){
        return perfumeRepository.guardarPerfume(perf);
    }

    // BUSCAR POR ID
    public PerfumeModel getPerfume(int id){
        return perfumeRepository.buscarPorID(id);
    }

    // ACTUALIZAR LIBRO
    public PerfumeModel updatePerfume(PerfumeModel perf){
        return perfumeRepository.actualizarPerfume(perf);
    }

    // ELIMINAR PERFUME 
    public String deletePerfume(int id){
        perfumeRepository.eliminarPorID(id);
        return "Se ha eliminado el perfume seleccionado";
    }

    public int totalPerfumes(){
        return perfumeRepository.retornarPerfumes().size();
    }

    public int totalPerfumesV2(){
        return perfumeRepository.totalPerfumes();
    }
}
