package com.projeto.luizaLabs.controller;

import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.repository.WishListRepository;
import com.projeto.luizaLabs.service.ProdutoService;
import com.projeto.luizaLabs.service.WishListService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.entity.WishList;
import com.projeto.luizaLabs.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WishListController {
    private static final int MAXSIZE = 20;
    @Autowired
    private WishListService wishlistService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;

    //Adicionar um produto na wishlist
    @ApiOperation(value = "Adicionar um produto na wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Foi adicionado um novo produto na sua wishlist", response = Response.class),
            @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
    })

    // Adicionar produto na wishlista do cliente
            // se ele não existe
                    //exibe mesg que não existe
            // se sim
                    // verifica se ele tem wishlist
                        // se ele não tem wishlist
                                //cria wishlist para o cliente e adicona o produto
                        // se tiver wishlist
                                //adicionar produto na wishlist do cliente

    @PutMapping("/wishlist/{id_cliente}/{id_produto}")
    public ResponseEntity<WishList> adicionarProdutosNaWishlist(@PathVariable long id_cliente, @PathVariable long id_produto) {
        //return wishlistService.criarWishList(wishlist);
        try {
            Optional<Cliente> cliente = clienteService.buscarCliente(id_cliente);
            System.out.println(cliente);
            if (cliente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Optional<WishList> wishListDoCliente = wishlistService.findByClientId(id_cliente);
                List<Produto> listaDeProdutoDoCliente = wishListDoCliente.get().getProduto();
                Produto produtoAdicionado  = produtoService.buscarProduto(id_produto);
                listaDeProdutoDoCliente.add(produtoAdicionado);
                wishListDoCliente.get().setProduto(listaDeProdutoDoCliente);
                wishlistService.atualizarWishlist(wishListDoCliente);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Visualizar wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Wishlist retornada com sucesso", response = Response.class),
            @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
    })

    // Buscar todas as  wishLists cadastradas
    @GetMapping("/wishlist")//wishlist pelo id
      public List<WishList> visualizarWishList(){
        Iterable<WishList> iterable = wishlistService.visualizarWishList();
        List<WishList> wishLists = new ArrayList<>();
        iterable.forEach(wishLists::add);
        return wishLists;
    }

   //Visualizar wishlist pelo Id do cliente
    @GetMapping("/wishlist/cliente/{id_cliente}")
    public ResponseEntity<WishList> visualizarWishListIdCliente(@PathVariable long id_cliente){
        try {
            Optional<Cliente> existeCliente = clienteService.findById(id_cliente);
            if (existeCliente == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            WishList wishList = wishlistService.findByClientId(id_cliente);
            if(wishList == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(wishList, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Deletar um produto na WishList
    @ApiOperation(value = "Deletar um produto na WishList")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto da wishlist deletado com sucesso", response = Response.class),
            @ApiResponse(code = 404, message = "Produto não encontrado na wishlist", response = Response.class),
            @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
    })
    @DeleteMapping("/wishlist/cliente/{id_cliente}/produto/{id_produto}")
    public ResponseEntity<WishList> removerProdutoNaWishlist(@PathVariable long id_produto, @PathVariable long id_cliente) {
        try {
            Optional<Cliente> existeCliente = clienteService.findById(id_cliente);
            WishList wishList = wishlistService.findByClientId(id_cliente);
            Optional<Produto> existeProduto = produtoService.findById(id_produto);

            if (existeCliente.isEmpty())
                return ResponseEntity.notFound().build();
            if (wishList == null)
                return ResponseEntity.notFound().build();
            if (!existeProduto.isPresent())
                return ResponseEntity.notFound().build();
            if (!wishList.deletarProduto(existeProduto.get()))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            wishList = wishlistService.criarWishList(wishList);
            return ResponseEntity.ok().body(wishList);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar se um determinado produto esta na wishlist do cliente

    @GetMapping("/wishlist/cliente/{id_cliente}/produto/{nome}")
    public ResponseEntity<WishList> buscarProdutoNaWishlistCliente(@PathVariable long id_cliente, @RequestParam String nome) {
        try {
            Optional<Cliente> existeCliente = clienteService.findById(id_cliente);
            WishList wishList = wishlistService.findByClientId(id_cliente);
            Optional<Produto> produto = produtoService.findByNome(nome);

            if (existeCliente.isEmpty())
                return ResponseEntity.notFound().build();
            if (wishList == null)
                return ResponseEntity.notFound().build();
            if (!produto.isPresent())
                return ResponseEntity.notFound().build();
            if (!wishList.existeProduto(produto.get()))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return ResponseEntity.ok().body(wishList);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

