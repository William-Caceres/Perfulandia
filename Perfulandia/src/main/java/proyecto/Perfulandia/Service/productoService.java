package proyecto.Perfulandia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.Perfulandia.Model.producto;
import proyecto.Perfulandia.Repository.productoRepository;

@Service
public class productoService {
    @Autowired
    private productoRepository productoRep;

    public List<producto> getAllProductos(){
        return productoRep.findAll();
    }

    public producto saveProducto(producto prod){
        return productoRep.save(prod);
    }
    
    public producto getSingleProduct(int id){
        return productoRep.findById(id);
    }

    public String deleteProducto(producto prod){
        productoRep.delete(prod);
        return "Producto eliminado";
    }

    public void updateProducto(int id, int newStock){
        
        producto productoActualizar = productoRep.findById(id);

        if (productoActualizar != null) {
            producto prod = productoActualizar;
            prod.setStock(newStock);
            productoRep.save(prod);
        }
    }

    public producto updateStock(int st){
        return productoRep.actualizarStock(st);
    }

    public int totalProductos(){
        return productoRep.findAll().size();
    }

    /*
    // OBTENER/RECUPERAR TODOS LOS ProductoS
    public List<producto> getProductoModels() {
        return productoRepository.retornarProductos();
    }

    // GUARDAR Producto
    public producto saveProducto(producto prod){
        return productoRepository.guardarProducto(prod);
    }

    // BUSCAR POR ID
    public producto getProducto(int id){
        return productoRepository.buscarPorID(id);
    }

    // ACTUALIZAR Producto
    public producto updateProducto(producto prod){
        return productoRepository.actualizarProducto(prod);
    }

    // ELIMINAR Producto 
    public String deleteProducto(int id){
        productoRepository.eliminarPorID(id);
        return "Se ha eliminado el Producto seleccionado";
    }

    public int totalProductos(){
        return productoRepository.retornarProductos().size();
    }

    public int totalProductosV2(){
        return productoRepository.totalProductos();
    }
    */
}
