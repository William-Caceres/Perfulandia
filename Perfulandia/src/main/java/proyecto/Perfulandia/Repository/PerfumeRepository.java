package proyecto.Perfulandia.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import proyecto.Perfulandia.Model.PerfumeModel;

@Repository
public class PerfumeRepository {
    
    //Lista para guardar los perfumes
    private List<PerfumeModel> listaPerfumes = new ArrayList<>();

    public PerfumeRepository() {

    }

    public List<PerfumeModel> retornarPerfumes()
    {// Regresar todos los libros del sistema, para mostrarlos en pantalla
        return listaPerfumes;
    }

    public PerfumeModel buscarPorID(int id)
    {// Buscar un perfume por el ID que este posee
        for (PerfumeModel perfume : listaPerfumes) {
            if(perfume.getId() == id){
                return perfume;
            }
        }
        return null;
    }

    public PerfumeModel buscarPorNombre(String nombre)
    {// Buscar un perfume por su nombre
        for (PerfumeModel perfume : listaPerfumes) {
            if(perfume.getNombre().equals(nombre)){
                return perfume;
            }
        }
        return null;
    }

    public PerfumeModel guardarPerfume(PerfumeModel perfumeNuevo)
    {
        // ID secuencial
        long newId = 1;
        for (PerfumeModel perfume : listaPerfumes) {
            if (perfume.getId() >= newId) {
                newId = perfume.getId() + 1;
            }
        }

        // Crear una nueva instancia con los datos del perfume recibido
        PerfumeModel p = new PerfumeModel();
        p.setId ((int) newId);
        p.setNombre(perfumeNuevo.getNombre());
        p.setMarca(perfumeNuevo.getMarca());
        p.setModelo(perfumeNuevo.getModelo());
        p.setPaisOrigen(perfumeNuevo.getPaisOrigen());
        p.setMl(perfumeNuevo.getMl());

        listaPerfumes.add(p);
        return p;
    }

    public PerfumeModel actualizarPerfume(PerfumeModel perfume)
    {// Actualizar perfume seleccionado
        int id = 0;
        int PosicionID = 0;
        
        for (int i = 0; i < listaPerfumes.size(); i++){
            if (listaPerfumes.get(i).getId() == perfume.getId()){
                id = perfume.getId();
                PosicionID = i;
            }
        }
        PerfumeModel p = new PerfumeModel();
        p.setId (id);
        p.setNombre(perfume.getNombre());
        p.setMarca(perfume.getMarca());
        p.setModelo(perfume.getModelo());
        p.setPaisOrigen(perfume.getPaisOrigen());
        p.setMl(perfume.getMl());

        listaPerfumes.set(id, perfume);
        return p;
    }
    
    public void eliminarPorID(int id)
    {
        listaPerfumes.removeIf(x -> x.getId() == id);
    }

    public int totalPerfumes()
    {
        return listaPerfumes.size();
    }
}
