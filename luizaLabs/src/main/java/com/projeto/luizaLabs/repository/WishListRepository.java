package com.projeto.luizaLabs.repository;

import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.entity.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    WishList findById(long id);

    //WishList findByCliente(Optional<Cliente> existeCliente);
    List<WishList> findByCliente(Optional<Cliente> existeCliente);


    /*@Query(value = "select p.* from Produto as p " +
    "inner join whishlist_produto as wp on wp.produto_id = p.ID " +
    "inner join whishlist as w on w.id = wp.WISH_LIST_ID " +
    "where w.id_cliente = :idcliente and wp.produto_id = :idproduto ", nativeQuery = true)
    Produto buscarProdutoWishList(@Param("idcliente") long idCliente,
                                  @Param("idproduto") long idProduto);*/


    /*@Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
    User findUserByStatusAndNameNamedParams(
            @Param("status") Integer status,
            @Param("name") String name);*/




    //List<WishList> listaProdutos(WishList produto);
    //o property listaProdutos found for type WishList!
    //WishList adicionarProdutosNaWishList(WishList produto);
    //WishList buscarProdutosNaWishList(WishList cliente);
}
