package com.example.repository;

import com.example.entity.CategoryEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @EntityGraph(attributePaths = "product")
    List<CategoryEntity> findAllByEnabledIsTrueOrderByIdCategoryDesc();

    @EntityGraph(attributePaths = "product")
    Optional<CategoryEntity> findFirstByEnabledIsTrueAndIdCategory (Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdCategoryNotAndNameIgnoreCase(Long id, String name);

}

