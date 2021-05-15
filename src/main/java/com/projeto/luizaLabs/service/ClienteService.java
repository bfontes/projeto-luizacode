package com.projeto.luizaLabs.service;

import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

   
    public Cliente adicionarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    
    public Optional<Cliente> buscarCliente(long id)
    {
        return clienteRepository.findById(id);
    }

 
    public Cliente atualizarCliente(Cliente cliente)
    {
        return clienteRepository.save(cliente);
    }

    public long quantidadeDeClientes(){
        return clienteRepository.count();
    }

    public Optional<Cliente> findByID(long id) {
        return clienteRepository.findById(id);
    }

}
