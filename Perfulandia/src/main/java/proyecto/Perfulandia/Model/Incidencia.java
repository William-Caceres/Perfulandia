package proyecto.Perfulandia.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
     @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    
    private String correo;
    private String comentario;
    
}