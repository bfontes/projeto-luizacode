package com.projeto.luizaLabs.repository;

import com.projeto.luizaLabs.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    WishList findById(long id);
}
