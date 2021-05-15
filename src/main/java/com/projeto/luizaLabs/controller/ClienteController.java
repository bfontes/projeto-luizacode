package com.projeto.luizaLabs.controller;

import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value="Adicionar um novo usuário")
    @ApiResponses(value={
            @ApiResponse(code=201,message= "Foi adicionado um novo usuario",response= Response.class),
            @ApiResponse(code=400,message="Requisição inválida",response=Response.class)
    })
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente respostaCliente = clienteService.adicionarCliente(cliente);
            return new ResponseEntity<>(clienteService.adicionarCliente(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @ApiOperation(value="Retornar um usuário")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Usuario retornado com sucesso",response= Response.class),
            @ApiResponse(code=400,message="Requisição inválida",response=Response.class)
    })
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable long id) {
        try {
            return new ResponseEntity<>(clienteService.buscarCliente(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   
    @ApiOperation(value="Atualizar um usuário")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Usuario retornado com sucesso",response= Response.class),
            @ApiResponse(code=404,message="Usuario nao encontrado",response = Response.class),
            @ApiResponse(code=400,message="Requisição inválida",response=Response.class)
    })
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable long id, Cliente cliente) {
        try {
            Optional<Cliente> clientePosBusca = clienteService.buscarCliente(id);
            if (clientePosBusca.isPresent()) {
                cliente.setID(clientePosBusca.get().getID());
                return new ResponseEntity<>(clienteService.atualizarCliente(cliente), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }


    @ApiOperation(value="Retornar a quantidade de usuarios")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Quantidade de usuários retornado com sucesso",response= Response.class),
            @ApiResponse(code=400,message="Requisição inválida",response=Response.class)
    })
    @GetMapping("/qtdeclientes")
    public long  qtdeClientes() {
        return clienteService.quantidadeDeClientes();
    }

}
