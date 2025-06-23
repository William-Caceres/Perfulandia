package proyecto.Perfulandia.Service;

import proyecto.Perfulandia.Model.Incidencia;
import proyecto.Perfulandia.Repository.IncidenciaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository inciRep;

    public List<Incidencia> getAllIncidencias() {
        return inciRep.findAll();
    }
    
    public Incidencia getSingleIncidencia(int id) {
        return inciRep.findById(id);
    }

    public Incidencia saveIncidencia(Incidencia inci){
        return inciRep.save(inci);
    }
}