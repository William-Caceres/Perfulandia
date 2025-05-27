package proyecto.Perfulandia.Model;

import java.util.Optional;

import jakarta.persistence.Entity;
import  jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String email;
    private String password;

    public static Optional<usuario> map(Object o) {
        throw new UnsupportedOperationException("Uniplemented method 'map'");
    }
}