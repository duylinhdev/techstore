package com.linh.techstore.specification;

import com.linh.techstore.dto.request.ProductFilterRequest;
import com.linh.techstore.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> filter(ProductFilterRequest filterRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filterRequest == null) {
                return criteriaBuilder.conjunction();
            }

            if (filterRequest.getBrandSlugs() != null && !filterRequest.getBrandSlugs().isEmpty()) {
                predicates.add(root.get("brand").get("slug").in(filterRequest.getBrandSlugs()));
            }

            if (filterRequest.getCategorySlugs() != null && !filterRequest.getCategorySlugs().isEmpty()) {
                predicates.add(root.get("category").get("slug").in(filterRequest.getCategorySlugs()));
            }

            if (filterRequest.getMinPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filterRequest.getMinPrice()));
            }

            if (filterRequest.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), filterRequest.getMaxPrice()));
            }

            if (StringUtils.hasText(filterRequest.getKeyword())) {
                String pattern = "%" + filterRequest.getKeyword().toLowerCase().trim() + "%";

                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern);

                Predicate brandNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("brand").get("name")), pattern);

                Predicate categoryNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("category").get("name")), pattern);

                predicates.add(criteriaBuilder.or(namePredicate, brandNamePredicate, categoryNamePredicate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
