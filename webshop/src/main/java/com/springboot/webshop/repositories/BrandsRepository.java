package com.springboot.webshop.repositories;

import com.springboot.webshop.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brand, String> {
}
