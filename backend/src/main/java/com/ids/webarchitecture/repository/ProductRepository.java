package com.ids.webarchitecture.repository;

import com.ids.webarchitecture.model.hibernate.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByAuthorId(String authorId);
    List<Product> findByTextLike(String substring);
}
