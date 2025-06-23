package proyecto.Perfulandia.assemblers;

//Importar las clases necesarias para el modelo y controlador
import proyecto.Perfulandia.Model.producto;
import proyecto.Perfulandia.Controller.productoControllerV3;

//Importar las clases static para crear los enlaces HATEOAS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//Importar la clase EntityModel para usar HATEOAS
import org.springframework.hateoas.EntityModel;

//Importar la interfaz RepresentationModelAssembler para crear el ensamblador de productoModelAssembler
import org.springframework.hateoas.server.RepresentationModelAssembler;

//Importar los tereotipos necesarios para el ensamblador
import org.springframework.stereotype.Component;

//Importar la anotacion NonNull para indicar que el metodo no acepta valores nulos
import org.springframework.lang.NonNull;

//Agregar la anotacion Component para indicar que nuestra clase productoModelAssembler es un componente Spring
@Component

//La clase productoModelAssembler debe implementar a RepresentationModelAssembler para convertir un objeto de producto en EntityModel

public class productoModelAssembler implements
RepresentationModelAssembler<producto, EntityModel<producto>>{
    //Anotacion Oberride para indicar que este metodo implementa un metodo de la interfaz RepresnationModelAssembler
    @Override
    //Metodo para convertir un objeto de producto a una EntityModel. Usamos la anotacion NonNull para no aceptar valores nulos, usamos linkTo con el metodo methodOn para crear los enlaces HATEOAS para cada metodo REST del controller
    public @NonNull EntityModel<producto> toModel (producto producto){
        return EntityModel.of(producto,
            linkTo(methodOn(productoControllerV3.class)
            .buscarProducto(producto.getId())).withRel("buscar"),
            linkTo(methodOn(productoControllerV3.class)
            .listarProductos()).withRel("listar")
            );
    }
}
