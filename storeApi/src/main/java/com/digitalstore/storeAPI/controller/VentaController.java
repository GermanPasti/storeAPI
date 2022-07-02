
package com.digitalstore.storeAPI.controller;

import com.digitalstore.storeAPI.dto.VentaProductoDTO;
import com.digitalstore.storeAPI.model.Cliente;
import com.digitalstore.storeAPI.model.Producto;
import com.digitalstore.storeAPI.model.Venta;
import com.digitalstore.storeAPI.service.IProductoService;
import com.digitalstore.storeAPI.service.IVentaService;
import java.time.LocalDate;
import java.util.Date;
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
public class VentaController {
    
    @Autowired
    private IVentaService ventServ;
    
    
    
    @GetMapping ("/ventas")
    public List <Venta> getVentas(){
        return ventServ.getVentas();
    }    
    
    
    @PostMapping ("/ventas/crear")
    public String saveVenta(@RequestBody Venta vent)
    {
    ventServ.saveVenta(vent);

    return "La venta fue creada correctamente";
    }
        
    
    @GetMapping ("/ventas/{codigo_venta}")
    public Venta findVenta (@PathVariable Long codigo_venta){
    return ventServ.findVenta(codigo_venta);
    }
    
       
    @DeleteMapping ("/ventas/eliminar/{codigo_venta}")
    public String deleteVenta(@PathVariable Long codigo_venta)
    {
    ventServ.deleteVenta(codigo_venta);
    return "La venta fue eliminada correctamente";
    } 
    
  
    
    
    @PutMapping("/ventas/editar/{codigo_venta}")
    public void editVenta(@PathVariable Long codigo_venta,
            @RequestParam (required = false, name ="codigo_nuevo") Long nuevaId,
            @RequestParam (required = false, name ="fecha") String nuevaFecha,
            @RequestParam (required = false, name ="total") Double nuevoTotal,
            @RequestParam (required = false, name ="listaProductos") List<Long> nuevaLista,
            @RequestParam (required = false, name ="unCliente") Long nuevoCliente)
      
    {
   
    //envío id original para buscar
    //envío nuevos datos para modificar
    ventServ.editVenta(codigo_venta, nuevaId, nuevaFecha, nuevoTotal, nuevaLista, nuevoCliente);

    }       
    
    
    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<Producto> productosDeUnaVenta(@PathVariable Long codigo_venta){
    return ventServ.productosDeUnaVenta(codigo_venta);   
    }
            
   @GetMapping ("/ventas/suma/{fecha}")
   public String SumaVentaDia (@PathVariable String fecha){
       
   return ventServ.ventaXDia(fecha);
   }
   
   @GetMapping ("/ventas/mayor_venta")
   public VentaProductoDTO devolverMayorV(){
   return ventServ.mayorVenta();
   }

}
