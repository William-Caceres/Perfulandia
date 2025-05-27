package proyecto.Perfulandia.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import proyecto.Perfulandia.Model.producto;

@Repository
public class productoRepository {
    
    //Lista para guardar los perfumes
    private List<producto> listaProductos = new ArrayList<>();

    public productoRepository() {
        // PERFUMES por defecto
        listaProductos.add(new producto(1,30000,"Le fragance du France","Fragossia","Clasico","Francia",85,200));
        listaProductos.add(new producto(2,45000,"Home escence","Gold escences","Clasico","USA",100,350));
        listaProductos.add(new producto(3,120000,"Blue ocean","Mystic Docks","Deluxe","Italia",60,150));
        listaProductos.add(new producto(4,90000,"Colisseu","Ol' Rome","Clasico","Roma",80,200));
    }

    public List<producto> retornarProductos()
    {// Regresar todos los libros del sistema, para mostrarlos en pantalla
        return listaProductos;
    }

    public producto buscarPorID(int id)
    {// Buscar un producto por el ID que este posee
        for (producto producto : listaProductos) {
            if(producto.getId() == id){
                return producto;
            }
        }
        return null;
    }

    public producto buscarPorNombre(String nombre)
    {// Buscar un producto por su nombre
        for (producto producto : listaProductos) {
            if(producto.getNombre().equals(nombre)){
                return producto;
            }
        }
        return null;
    }

    public producto guardarProducto(producto productoNuevo)
    {
        // ID secuencial
        long newId = 1;
        for (producto producto : listaProductos) {
            if (producto.getId() >= newId) {
                newId = producto.getId() + 1;
            }
        }

        // Crear una nueva instancia con los datos del producto recibido
        producto p = new producto();
        p.setId ((int) newId);
        p.setPrecio(productoNuevo.getPrecio());
        p.setNombre(productoNuevo.getNombre());
        p.setMarca(productoNuevo.getMarca());
        p.setModelo(productoNuevo.getModelo());
        p.setPaisOrigen(productoNuevo.getPaisOrigen());
        p.setMl(productoNuevo.getMl());
        p.setStock(productoNuevo.getStock());

        listaProductos.add(p);
        return p;
    }

    public producto actualizarProducto(producto producto)
    {// Actualizar producto seleccionado
        int id = 0;
        int PosicionID = 0;
        
        for (int i = 0; i < listaProductos.size(); i++){
            if (listaProductos.get(i).getId() == producto.getId()){
                id = producto.getId();
                PosicionID = i;
            }
        }
        producto p = new producto();
        p.setId (id);
        p.setPrecio(producto.getPrecio());
        p.setNombre(producto.getNombre());
        p.setMarca(producto.getMarca());
        p.setModelo(producto.getModelo());
        p.setPaisOrigen(producto.getPaisOrigen());
        p.setMl(producto.getMl());
        p.setStock(producto.getStock());

        listaProductos.set(id, producto);
        return p;
    }
    
    public void eliminarPorID(int id)
    {
        listaProductos.removeIf(x -> x.getId() == id);
    }

    public int totalProductos()
    {
        return listaProductos.size();
    }
}
