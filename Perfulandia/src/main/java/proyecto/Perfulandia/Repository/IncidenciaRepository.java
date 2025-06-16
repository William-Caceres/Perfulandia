package proyecto.Perfulandia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.Perfulandia.Model.Incidencia;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long>{
    
    Incidencia findById(int id);
}