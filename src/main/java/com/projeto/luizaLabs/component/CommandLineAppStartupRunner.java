package com.projeto.luizaLabs.component;

import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private ClienteService clienteService;

    @Override
    public void run(String...args)
    {
        this.criarCliente();
    }

    private void criarCliente()
    {
        Cliente clienteBia = new Cliente();
        clienteBia.setCpf("23456789876");
        clienteBia.setNome("bia");
        clienteService.adicionarCliente(clienteBia);
    }

}
