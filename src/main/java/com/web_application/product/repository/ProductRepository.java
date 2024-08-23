package com.web_application.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_application.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { 
      
}
