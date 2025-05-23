package proyecto.Perfulandia.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //
@AllArgsConstructor 
@NoArgsConstructor 
public class PerfumeModel {
    private int id;

    private String nombre;
    private String marca;
    private String modelo;
    private String paisOrigen;
    private int ml;
    
}
