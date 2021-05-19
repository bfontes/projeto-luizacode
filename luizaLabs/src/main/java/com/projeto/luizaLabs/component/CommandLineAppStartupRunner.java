package com.projeto.luizaLabs.component;

import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.entity.WishList;
import com.projeto.luizaLabs.service.ClienteService;
import com.projeto.luizaLabs.service.ProdutoService;
import com.projeto.luizaLabs.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private WishListService wishlistService;

    @Override
    public void run(String...args)
    {
        this.criarCliente();
        this.criarProduto();
        this.criarWishlist();
    }

    private void criarCliente()
    {
        Cliente clienteBia = new Cliente();
        clienteBia.setCpf("23456789876");
        clienteBia.setNome("bia");
        clienteService.adicionarCliente(clienteBia);
    }

    private void criarProduto()
    {
        Produto produto = new Produto();
        produto.setNome("Geladeira");
        produto.setDescricao("Essa geladeira é muito legal");
        //Produto create  = produtoService.adicionarProduto(produto);
        //produto.add(create);

        BigDecimal geladeira = new BigDecimal(1000);
        produto.setPreco(geladeira);
        produtoService.adicionarProduto(produto);
    }
    private void criarWishlist() {
        //criando uma lista
        List<Produto> produto = new ArrayList<Produto>();
        WishList wishlist = new WishList();

        //criando um cliente
        Cliente clienteCarol = new Cliente();
        clienteCarol.setCpf("12345678987");
        clienteCarol.setNome("Carol");
        clienteService.adicionarCliente(clienteCarol);

        //criando o produto
        Produto prod = new Produto();
        prod.setNome("Televisão");
        prod.setDescricao("televisão mais linda da nossa vida");
        BigDecimal valor = new BigDecimal(10);
        prod.setPreco(valor);
        Produto create = produtoService.adicionarProduto(prod);

        produto.add(create);
        wishlist.setCliente(clienteCarol);
        wishlist.setProduto(produto);
//        BigDecimal total = new BigDecimal(10);
//        wishlist.setTotal(total);

        wishlistService.criarWishList(wishlist);
    }
}
