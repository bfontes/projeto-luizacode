package com.projeto.luizaLabs.service;

import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto adicionarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }


    public Produto buscarProduto(long id) {
        return produtoRepository.findById(id);
    }


    public Produto atualizaProduto(Object object) {
        return produtoRepository.save((Produto) object);
    }


    public long quantidadeDeProdutos(){
        return produtoRepository.count();
    }
}