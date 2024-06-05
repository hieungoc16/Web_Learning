package com.springboot.webshop.repositories;

import com.springboot.webshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, String> {
}


