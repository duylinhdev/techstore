package com.linh.techstore.repository;

import com.linh.techstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @EntityGraph(attributePaths = {"category", "brand"})
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    @EntityGraph(attributePaths = {"category", "brand"})
    List<Product> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @EntityGraph(attributePaths = {"category", "brand"})
    List<Product> findAllByIsFeaturedTrueOrderByCreatedAtDesc(Pageable pageable);

    @EntityGraph(attributePaths = {"category", "brand"})
    Optional<Product> findBySlug(String slug);

    List<Product> findByCategory_SlugAndSlugNot(String category, String slug, Pageable pageable);

}
