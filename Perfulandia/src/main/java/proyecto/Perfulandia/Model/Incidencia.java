package proyecto.Perfulandia.Model;


import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name= "incidencia")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Incidencia {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    
    private String usuario;
    private String opinion;
    private String mejora;
    private int calificacion;
    
    public static Optional<producto> map(Object o) {
        throw new UnsupportedOperationException("Uniplemented method 'map'");
    }
}