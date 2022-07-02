
package com.digitalstore.storeAPI.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)     
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    @ManyToMany  
    @JoinTable(
            name = "venta_producto",
            joinColumns = @JoinColumn (name = "codigo_venta", nullable = false),
            inverseJoinColumns = @JoinColumn (name = "codigo_producto", nullable = false)
    )     
    List <Producto> listaProductos;
    @OneToOne      
    Cliente UnCliente;

    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente UnCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.UnCliente = UnCliente;
    }

    
    
    
}
