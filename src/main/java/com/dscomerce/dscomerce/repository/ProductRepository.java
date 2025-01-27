package com.dscomerce.dscomerce.repository;

import com.dscomerce.dscomerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
