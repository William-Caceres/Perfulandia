package proyecto.Perfulandia.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import proyecto.Perfulandia.Model.Incidencia;

@Repository
public class IncidenciaRepository {
    
     private List<Incidencia> listaIncidencias = new ArrayList<>();

     public Incidencia guardarIncidencia(Incidencia newIncidencia){
        long newId = 1;
        for (Incidencia incidencia : listaIncidencias) {
            if (incidencia.getId() >= newId) {
                newId = incidencia.getId() + 1;
            }
        }
        // Crear una nueva instancia con los datos de la incidencia recibida
        Incidencia i = new Incidencia();
        i.setId((int)newId);
        i.setNombreOpinion(newIncidencia.getNombreOpinion());
        i.setOpinion(newIncidencia.getOpinion());
        i.setMejora(newIncidencia.getMejora());
        i.setCalificacion(newIncidencia.getCalificacion());

        listaIncidencias.add(i);
        return i;
    }   

    public List<Incidencia> retorIncidencias()
    {
        return listaIncidencias;
    }
}