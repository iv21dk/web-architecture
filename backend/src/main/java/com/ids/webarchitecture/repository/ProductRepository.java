package com.ids.webarchitecture.repository;

import com.ids.webarchitecture.model.hibernate.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByAuthorId(String authorId);
    List<Product> findByTextLike(String substring);
}
