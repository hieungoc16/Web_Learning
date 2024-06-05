package com.springboot.webshop.repositories;

import com.springboot.webshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, String> {

}

