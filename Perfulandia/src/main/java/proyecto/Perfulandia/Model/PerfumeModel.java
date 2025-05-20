package proyecto.Perfulandia.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class PerfumeModel {
    private int id;

    private String modelo;
    private String marca;
    private String PaisOrigen;
    private int ml;
    
}
