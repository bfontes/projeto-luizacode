//package com.projeto.luizaLabs;
//
//import com.projeto.luizaLabs.entity.Produto;
//import com.projeto.luizaLabs.service.ProdutoService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Transactional
//public class ProdutoServiceTest {
//
//    @Autowired
//    ProdutoService produtoServiceTest;
//
//    @Test
//    void salvarProdutoNoBanco() {
//    Produto produto = new Produto();
//    produto.setPreco(1000.0);
//    produto.setNome("Geladeira");
//
//    Produto produtoSalvo = produtoServiceTest.adicionarProduto(produto);
//    assertThat(produtoSalvo).isNotNull();
//    }
//
//    @Test
//    void verificarIdDoProdutoSalvo() {
//    Produto produto = new Produto();
//    produto.setPreco(1000.0);
//    produto.setNome("Geladeira");
//    Produto produtoSalvo = produtoServiceTest.adicionarProduto(produto);
//    assertThat(produtoSalvo.getID()).isEqualTo(1);
//    }
//
//    @Test
//    public void getProdutoByID() {
//    Produto produto = new Produto();
//    produto.setPreco(1000.0);
//    produto.setNome("Geladeira");
//    produtoServiceTest.adicionarProduto(produto);
//    Produto found = produtoServiceTest.buscarProduto(produto.getID());
//    assertThat(found.getNome()).isEqualTo(produto.getNome());
//    }
//
//    @Test
//    public void contaQuantosProdutosNoBanco() {
//        Produto produto = new Produto();
//        produto.setPreco(1000.0);
//        produto.setNome("Geladeira");
//    produtoServiceTest.adicionarProduto(produto);
//    long qtd = produtoServiceTest.quantidadeDeProdutos();
//    assertThat(qtd).isEqualTo(1);
//    }
//}