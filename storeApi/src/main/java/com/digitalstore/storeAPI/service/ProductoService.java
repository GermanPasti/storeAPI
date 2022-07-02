
package com.digitalstore.storeAPI.service;

import com.digitalstore.storeAPI.model.Producto;
import com.digitalstore.storeAPI.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    @Autowired 
    private IProductoRepository  prodRepository;

    @Override
    public List<Producto> getProductos() {
        List<Producto> listaProductos = prodRepository.findAll();
        return listaProductos;
    }

    @Override
    public void saveProducto(Producto prod) {
        prodRepository.save(prod);
    }

    @Override
    public void deleteProducto(Long codigo_producto) {
        prodRepository.deleteById(codigo_producto);
    }

    @Override
    public Producto findProducto(Long codigo_producto) {
        return prodRepository.findById(codigo_producto).orElse(null);
    }

    @Override
    public void editProducto(Long id_original, Long idNueva, String nuevoNombre, String nuevaMarca, Double nuevoCosto, Double nuevaCantidad) {
        
             //busco  el objeto original
            Producto prod = this.findProducto(id_original);
            
            //proceso de modificación a nivel lógico
            prod.setCodigo_producto(idNueva);
            prod.setNombre(nuevoNombre);
            prod.setMarca(nuevaMarca);
            prod.setCosto(nuevoCosto);
            prod.setCantidad_disponible(nuevaCantidad);
            
            //guardar los cambios
            this.saveProducto(prod);
    }

    @Override
    public List<Producto> getFaltantes() {
        //busco todos los productos
        List<Producto> listaProductos = getProductos();
        
        //la lista que enviaré
        List<Producto> prodFalt = new ArrayList<>();
        
        for(Producto p: listaProductos)
         {
           if ((p.getCantidad_disponible()) < 5 )
            {
             prodFalt.add(p);
            }
         }
        
        return prodFalt;
    }

    
}
