package com.projeto.luizaLabs.service;

import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.entity.WishList;
import com.projeto.luizaLabs.repository.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    private WishListRepository wishListRepository;

    //Adicionar a lista no banco
    public WishList adicionarWishList(WishList wishlist){
        return wishListRepository.save(wishlist);
    }

    public WishList buscarWishListId(long id) {
        return wishListRepository.findById(id);
    }
    public WishList buscarClienteWishlist(long id_cliente){
        return  wishListRepository.buscarCliente(id_cliente);
    }

    public WishList buscarProductoWishlist(long id_produto){
        return  wishListRepository.buscarProduto(id_produto);
    }

    public WishList atualizarWishList(Object object) {
        return wishListRepository.save((WishList) object);
    }

}
