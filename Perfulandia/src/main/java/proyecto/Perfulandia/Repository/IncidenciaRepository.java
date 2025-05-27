package proyecto.Perfulandia.Repository;

import java.util.ArrayList;
import java.util.List;

import proyecto.Perfulandia.Model.Incidencia;

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
        i.setId(newIncidencia.getId());
        i.setCorreo(newIncidencia.getCorreo());
        i.setComentario(newIncidencia.getComentario());

        listaIncidencias.add(i);
        return i;
    }
    
}
