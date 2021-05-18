package com.projeto.luizaLabs.service;

import com.projeto.luizaLabs.entity.Cliente;
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

    //Criar uma wishlist
    public WishList criarWishList(WishList wishlist){
        return wishListRepository.save(wishlist);
    }

    public WishList atualizarWishlist(Optional<WishList> wishlist){
        return wishListRepository.save(wishlist);
    }


    //Visualizar todos os produtos na WishList
    public List<WishList> visualizarWishList(){
        Iterable<WishList> iterable = wishListRepository.findAll();
        List<WishList> wishLists = new ArrayList<>();
        iterable.forEach(wishLists::add);
        return wishLists;
    }

    //Metodo para saber se o cliente existe
    public Optional<WishList> findByClientId(Long id) {
//        Optional<Cliente> existeCliente = clienteService.findById(id);
//        if (existeCliente.isPresent()) {
//            List<WishList> listWishlist = wishListRepository.findByCliente(existeCliente);
//            if (!listWishlist.isEmpty()) {
//                return listWishlist.get(0);
//            }
//        }
//        return null;
        return wishListRepository.findByClienteId(id);
    }

}
