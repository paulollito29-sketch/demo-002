package com.example.repository;

import com.example.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByEnabledIsTrueOrderByIdProductDesc();

    Optional<ProductEntity> findFirstByEnabledIsTrueAndIdProduct(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndNameIgnoreCaseAndIdProductNot(String name, Long id);

    @Query(value = """
        SELECT *
        FROM products
        WHERE enabled = true
          AND category_id = :categoryId
        ORDER BY id_product DESC
        """, nativeQuery = true)
    List<ProductEntity> findAllByCategoryId(@Param("categoryId") Long categoryId);


    List<ProductEntity> findAllByEnabledIsTrue();

    List<ProductEntity> findAllByEnabledIsTrueOrderByCategory_IdCategoryAscIdProductDesc();
}
