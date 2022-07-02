
package com.digitalstore.storeAPI.controller;

import com.digitalstore.storeAPI.model.Producto;
import com.digitalstore.storeAPI.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
    
    @Autowired
    private IProductoService prodServ;
    
    @GetMapping ("/productos")
    public List <Producto> getPersonas(){
        return prodServ.getProductos();
    }    
    
    
    @PostMapping ("/productos/crear")
    public String saveProducto(@RequestBody Producto prod)
    {
    prodServ.saveProducto(prod);
    return "El producto fue creado correctamente";
    }
        
    @GetMapping ("/productos/{codigo_producto}")
    public Producto prod (@PathVariable Long codigo_producto){
    return prodServ.findProducto(codigo_producto);
    }
    
       
    @DeleteMapping ("/productos/eliminar/{codigo_producto}")
    public String deleteProducto(@PathVariable Long codigo_producto)
    {
    prodServ.deleteProducto(codigo_producto);
    return "El producto fue eliminado correctamente";
    } 
    
  
    
    
    @PutMapping("/productos/editar/{codigo_producto}")
    public void editProducto(@PathVariable Long codigo_producto,
            @RequestParam (required = false, name ="codigo_nuevo") Long nuevaId,
            @RequestParam (required = false, name ="nombre") String nuevoNombre,
            @RequestParam (required = false, name ="marca") String nuevaMarca,
            @RequestParam (required = false, name ="costo") Double nuevoCosto,
            @RequestParam (required = false, name ="cantidad_disponible") Double nuevaCantidad)
    {
    //envío id original para buscar
    //envío nuevos datos para modificar
    prodServ.editProducto(codigo_producto, nuevaId, nuevoNombre, nuevaMarca, nuevoCosto, nuevaCantidad);

    }        
   
    
    @GetMapping ("/productos/falta_stock")
     public List<Producto> getFaltantes() {
     
         List<Producto> listaFaltantes = prodServ.getFaltantes();
         return listaFaltantes;
     }
    
    
    
}
