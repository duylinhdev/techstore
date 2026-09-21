package com.linh.techstore.repository;

import com.linh.techstore.entity.Brand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {


    @Query("""
            select distinct p.brand from Product p where p.category.slug = :categorySlug
            """)
    List<Brand> findBrandsByCategorySlug(@Param("categorySlug") String categorySlug, Pageable pageable);

}
