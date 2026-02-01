package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.model.Product;

@Repository
public interface ProductReository extends JpaRepository<Product, Integer> {

    @Query("""
            SELECT p FROM Product p
            WHERE lower(p.name) LIKE lower(concat('%', :keyword, '%'))
               OR lower(p.description) LIKE lower(concat('%', :keyword, '%'))
               OR lower(p.brand) LIKE lower(concat('%', :keyword, '%'))
               OR lower(p.category) LIKE lower(concat('%', :keyword, '%'))
        """)
	List<Product> searchProduct(String keyword);

}
