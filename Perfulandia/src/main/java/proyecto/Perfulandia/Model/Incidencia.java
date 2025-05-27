package proyecto.Perfulandia.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name= "Incidencia")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Incidencia {
    private int Id;
    private String correo;
    private String comentario;
    
}