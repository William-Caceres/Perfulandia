package proyecto.Perfulandia.Model;

import java.util.Optional;

import jakarta.persistence.Entity;
import  jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String email;
    private String password;

    public static Optional<UsuarioModel> map(Object o) {
        throw new UnsupportedOperationException("Uniplemented method 'map'");
    }
}
