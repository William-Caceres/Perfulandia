package proyecto.Perfulandia.Model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "perfume")
@Data //
@AllArgsConstructor 
@NoArgsConstructor 
public class PerfumeModel {

    private int id;

    private int precio;
    private String nombre;
    private String marca;
    private String modelo;
    private String paisOrigen;
    private int ml;
    private int stock; 

    public static Optional<PerfumeModel> map(Object o) {
        throw new UnsupportedOperationException("Uniplemented method 'map'");
    }
    
}
