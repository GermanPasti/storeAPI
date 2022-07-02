
package com.digitalstore.storeAPI.service;

import com.digitalstore.storeAPI.dto.VentaProductoDTO;
import com.digitalstore.storeAPI.model.Cliente;
import com.digitalstore.storeAPI.model.Producto;
import com.digitalstore.storeAPI.model.Venta;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IVentaService {
    
    //para traer todas las ventas
    public List <Venta> getVentas ();
    
    //para dar un alta a una venta
    public void saveVenta (Venta vent);
    
    //para borrar una venta
    public void deleteVenta (Long codigo_venta);
    
    //para encontrar una venta
    public Venta findVenta (Long codigo_venta);
    
    //edición/modificación
    public void editVenta (Long idOriginal, Long idNueva,
                           String fecha, 
                           Double total, List<Long> listaProductos,
                           Long unCliente);
    
    //lista de productos de una venta
    public List <Producto> productosDeUnaVenta (Long codigo_venta);
    
    
    //monto y cant de ventas en un día
    public String ventaXDia (String fecha);
    
    //venta con mayor monto
    public VentaProductoDTO mayorVenta();
    

}
