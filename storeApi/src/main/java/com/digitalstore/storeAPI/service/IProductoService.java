
package com.digitalstore.storeAPI.service;

import com.digitalstore.storeAPI.model.Producto;
import java.util.List;

public interface IProductoService {
    
    //para traer todos los productos
    public List <Producto> getProductos ();
    
    //para dar un alta a un producto
    public void saveProducto (Producto prod);
    
    //para borrar un producto
    public void deleteProducto (Long codigo_producto);
    
    //para encontrar un producto
    public Producto findProducto (Long codigo_producto);
    
    //edición/modificación
    public void editProducto (Long idOriginal, Long idNueva,
                           String nombre, String marca,
                           Double costo, Double cantidad_disponible);
    
    //obtener productos que haya menos de 5
    public List <Producto> getFaltantes();
    

    
}
