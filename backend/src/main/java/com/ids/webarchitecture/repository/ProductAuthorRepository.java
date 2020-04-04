package com.ids.webarchitecture.repository;

import com.ids.webarchitecture.model.hibernate.ProductAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductAuthorRepository extends JpaRepository<ProductAuthor, String> {
    List<ProductAuthor> findByAuthorTemplateId(String authorTemplateId);

}
