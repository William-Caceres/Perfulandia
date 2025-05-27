package proyecto.Perfulandia.Service;

import proyecto.Perfulandia.Model.Incidencia;
import proyecto.Perfulandia.Repository.IncidenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository inciRep;
    public Incidencia saveIncidencia(Incidencia inci){
        return inciRep.guardarIncidencia(inci);
    }
}
