package com.projeto.luizaLabs.service;

import antlr.ASTNULLType;
import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.entity.WishList;
import com.projeto.luizaLabs.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private ClienteService clienteService;


    //Adicionar a lista no banco
    public WishList adicionarProdutosNaWishList(WishList wishlist){
        return wishListRepository.save(wishlist);
    }


    //Visualizar todos os produtos na WishList
    public List<WishList> visualizarWishList(){
        Iterable<WishList> iterable = wishListRepository.findAll();
        List<WishList> wishLists = new ArrayList<>();
        iterable.forEach(wishLists::add);
        return wishLists;
    }

    /*public WishList buscarWishListPeloCliente(Cliente cliente) {
        return wishListRepository.findByCliente(cliente);
    }*/
    //Metodo para saber se o cliente existe
    public WishList findByClientId(Long id) {

        Optional<Cliente> existeCliente = clienteService.findById(id);
        if (existeCliente.isPresent()) {
            List<WishList> listWishlist = wishListRepository.findByCliente(existeCliente);
            if (!listWishlist.isEmpty()) {
                return listWishlist.get(0);
            }
        }
        return null;
    }

    /*//Buscar produto na WishList
    public Produto buscarProdutosNaWishList(long idCliente, long idProduto) {
        return wishListRepository.buscarProdutoWishList(idCliente, idProduto);
    }*/

    //Visualizar produtos na wishlist
//    public List<WishList> visualizarProdutosNaWishList(Produto produto) {
//        return wishListRepository.findAll();
//    }

//    public WishList atualizarProdutoWishList(Object object) {
//        return wishListRepository.save((WishList) object);
//    }
}
