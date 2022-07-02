
package com.digitalstore.storeAPI.dto;

import com.digitalstore.storeAPI.model.Producto;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter  @Setter
public class VentaProductoDTO implements Serializable{
    
    private Long codigo_venta;
    private Double total; 
    private int cant_prod;
    private String nombre;
    private String apellido;  

    public VentaProductoDTO() {
    }

    public VentaProductoDTO(Long codigo_venta, Double total, int cant_prod, String nombre, String apellido) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cant_prod = cant_prod;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    
}
