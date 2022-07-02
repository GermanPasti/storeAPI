
package com.digitalstore.storeAPI.service;

import com.digitalstore.storeAPI.dto.VentaProductoDTO;
import com.digitalstore.storeAPI.model.Cliente;
import com.digitalstore.storeAPI.model.Producto;
import com.digitalstore.storeAPI.model.Venta;
import com.digitalstore.storeAPI.repository.IClienteRepository;
import com.digitalstore.storeAPI.repository.IProductoRepository;
import com.digitalstore.storeAPI.repository.IVentaRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private IVentaRepository ventRepository;
    
    @Autowired
    private IProductoRepository prodRepository;
        
        @Autowired
    private IClienteRepository cliRepository;
        
    
    @Override
    public List<Venta> getVentas() {
        List<Venta> listaVentas = ventRepository.findAll();
        return listaVentas;
    }

    @Override
    public void saveVenta(Venta vent) {
        ventRepository.save(vent);
        
       

    }

    @Override
    public void deleteVenta(Long codigo_venta) {
        ventRepository.deleteById(codigo_venta);
    }

    @Override
    public Venta findVenta(Long codigo_venta) {
        return ventRepository.findById(codigo_venta).orElse(null);
    }

    @Override
    public void editVenta(Long idOriginal, Long idNueva, String fecha, Double total, List<Long> listaProductos, Long unCliente) {
        
            //Busco los productos de la base de datos relacionados con la lista mandada      
            List<Producto> lista = new ArrayList <Producto>();
            for (Long i: listaProductos)
              {
              lista.add(prodRepository.findById(i).orElse(null));
              
              }
            
            //traigo el mismo cliente pero desde la base de datos
            Cliente cliente = cliRepository.findById(unCliente).orElse(null);
            
        
             LocalDate fecha_venta = LocalDate.parse(fecha);
        
            //busco  el objeto original
            Venta vent = this.findVenta(idOriginal);
            
            //proceso de modificación a nivel lógico
            vent.setCodigo_venta(idNueva);
            vent.setFecha_venta(fecha_venta);
            vent.setTotal(total);
            vent.setListaProductos(lista);
            vent.setUnCliente(cliente);
            
            //guardar los cambios
            this.saveVenta(vent);
    }

    @Override
    public List<Producto> productosDeUnaVenta(Long codigo_venta) {
    
           //busco la venta determinada
           Venta vent = findVenta(codigo_venta);

           return vent.getListaProductos();      
    }

    @Override
    public String ventaXDia(String fecha) {
        
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        
        LocalDate fecha_venta = LocalDate.parse(fecha);

        
        //busco todas las ventas
        List<Venta> ventas = getVentas();
        //busco las ventas con la fecha asignada
        List<Venta> ventasFecha = new ArrayList<Venta>();
        for(Venta v: ventas)
          {
          if((v.getFecha_venta()).equals(fecha_venta))
             {
             ventasFecha.add(v);
             }
          }
        
        
        //respondo a la consulta
        Double sumaMontos = 0.0;
        int sumaCant =0 ; 
        
        if(ventasFecha != null)
          {
           for(Venta vf: ventasFecha)
             {
                 sumaMontos = sumaMontos + vf.getTotal();
                 sumaCant = sumaCant + 1;
             }
          }
        return "la cantidad de ventas fue de " +sumaCant+ "y el monto total fue de "+sumaMontos;
    }

    @Override
    public VentaProductoDTO mayorVenta() {
           
        VentaProductoDTO datosMVenta = new VentaProductoDTO();
        
        //busco todas las ventas
        List<Venta> ventas = getVentas();

        
      if(ventas != null){  
        //busco el mayor monto y guardo el código
     
           Double ref = 0.0;
           Long codGanador = 0l;
        
           for(Venta v: ventas)
             {
              if ((v.getTotal()) > ref)
                {
                ref = v.getTotal();
                codGanador = v.getCodigo_venta();
                }
              }

           //busco la venta por código y armo el objeto DTO
           Venta ventaMayor = findVenta(codGanador);  
           
           int cantProd = ( ventaMayor.getListaProductos() ).size()   ;
           

           datosMVenta.setCodigo_venta(codGanador);
           datosMVenta.setTotal(ref);
           datosMVenta.setCant_prod(cantProd);
           datosMVenta.setNombre(ventaMayor.getUnCliente().getNombre() );
           datosMVenta.setApellido(ventaMayor.getUnCliente().getApellido() );
    
       }    
       return datosMVenta;    
    }


    
}