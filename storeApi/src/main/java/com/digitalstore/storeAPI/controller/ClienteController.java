
package com.digitalstore.storeAPI.controller;

import com.digitalstore.storeAPI.model.Cliente;
import com.digitalstore.storeAPI.service.IClienteService;
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
public class ClienteController {
        
    @Autowired
    private IClienteService cliServ;
    
     @GetMapping ("/clientes/traer")
     public List<Cliente> getClientes(){
     return cliServ.getClientes();
     }
    
    @PostMapping ("/clientes/crear")   
    public String saveCliente(@RequestBody Cliente cli)
    {
    cliServ.saveCliente(cli);
    return "El cliente fue creado correctamente";
    }  
    
    @GetMapping ("/clientes/{id_cliente}")
    public Cliente cliente (@PathVariable Long id_cliente){
    return cliServ.findCliente(id_cliente);
    }
    
    
    @DeleteMapping ("/clientes/eliminar/{id_cliente}")
    public String deleteCliente(@PathVariable Long id_cliente)
    {
    cliServ.deleteCliente(id_cliente);
    return "La persona fue eliminada correctamente";
    }
    
       @PutMapping("/clientes/editar/{id_cliente}")
    public Cliente editCliente(@PathVariable Long id_cliente,
            @RequestParam (required = false, name ="id") Long nuevaId,
            @RequestParam (required = false, name ="nombre") String nuevoNombre,
            @RequestParam (required = false, name ="apellido") String nuevoApellido,
            @RequestParam (required = false, name ="dni") String nuevoDni)
    {
    //envío id original para buscar
    //envío nuevos datos para modificar
    cliServ.editCliente(id_cliente, nuevaId, nuevoNombre, nuevoApellido, nuevoDni);
     
    //busco el cliente editado para mostrar en la response
    Cliente cli = cliServ.findCliente(nuevaId);
    
    return cli;
    }       
    
}
