package com.ids.webarchitecture.repository;

import com.ids.webarchitecture.model.hibernate.ProductAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAuthorRepository extends JpaRepository<ProductAuthor, String> {
    <T> List<T> findByAuthorTemplateId(String authorTemplateId, Class<T> projection);
    @Query("select a from ProductAuthor a left join fetch a.products where a.id = :authorId")
    ProductAuthor findByIdFull(@Param("authorId") String authorId);
}
