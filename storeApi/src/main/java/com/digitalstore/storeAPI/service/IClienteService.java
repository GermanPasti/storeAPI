
package com.digitalstore.storeAPI.service;

import com.digitalstore.storeAPI.model.Cliente;
import java.util.List;

public interface IClienteService {
    
    //para traer todos los clientes
    public List <Cliente> getClientes ();
    
    //para dar un alta a un cliente
    public void saveCliente (Cliente cli);
    
    //para borrar un cliente
    public void deleteCliente (Long id_cliente);
    
    //para encontrar un cliente
    public Cliente findCliente (Long id_cliente);
    
    //edición/modificación
    public void editCliente (Long idOriginal, Long idNueva,
                           String nombre, String apellido,
                           String dni);
        
    
}
