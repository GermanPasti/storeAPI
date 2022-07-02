
package com.digitalstore.storeAPI.service;

import com.digitalstore.storeAPI.model.Cliente;
import com.digitalstore.storeAPI.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    private IClienteRepository cliRepository;    

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = cliRepository.findAll();
        return listaClientes;
    }

    @Override
    public void saveCliente(Cliente cli) {
         cliRepository.save(cli);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
          cliRepository.deleteById(id_cliente);
    }

    @Override
    public Cliente findCliente(Long id_cliente) {
        return cliRepository.findById(id_cliente).orElse(null);
    }

    @Override
    public void editCliente(Long idOriginal, Long idNueva, String nombre, String apellido, String dni) {
        
              //busco  el objeto original
            Cliente cli = this.findCliente(idOriginal);
            
            //proceso de modificación a nivel lógico
            cli.setId_cliente(idNueva);
            cli.setNombre(nombre);
            cli.setApellido(apellido);
            cli.setDni(dni);
            
            //guardar los cambios
            this.saveCliente(cli);
        
    }
    
}
