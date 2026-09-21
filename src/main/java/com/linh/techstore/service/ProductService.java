package com.linh.techstore.service;

import com.linh.techstore.converter.ProductConverter;
import com.linh.techstore.dto.request.ProductFilterRequest;
import com.linh.techstore.dto.response.PaginationResult;
import com.linh.techstore.dto.response.ProductResponse;
import com.linh.techstore.entity.Product;
import com.linh.techstore.exception.ResourceNotFoundException;
import com.linh.techstore.repository.ProductRepository;
import com.linh.techstore.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 20;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "name", "price", "createdAt");

    private List<Integer> calcNavigationPages(
            int currentPage,
            int totalPages,
            int maxNavPage
    ) {
        List<Integer> navPages = new ArrayList<>();

        if (totalPages <= 0) {
            return navPages;
        }

        if (totalPages <= maxNavPage) {
            for (int page = 1; page < totalPages; page++) {
                navPages.add(page);
            }
            return navPages;
        }

        int current = Math.min(Math.max(currentPage, 1), totalPages);

        int begin = Math.max(1, current - maxNavPage / 2);

        int end = Math.min(totalPages, current + maxNavPage / 2);

        navPages.add(1);

        if (begin > 2) {
            navPages.add(-1);
        }
        for (int page = begin; page <= end; page++) {
            if (page > 1 && page <= totalPages) {
                navPages.add(page);
            }
        }

        if (end < totalPages - 1) {
            navPages.add(-1);
        }

        navPages.add(totalPages);

        return navPages;
    }

    public PaginationResult<ProductResponse> getProductsWithPagination(ProductFilterRequest filter, int page, int pageSize, String sortBy, String sortOrder, int maxNavPage) {
        page = Math.max(page, 1);

        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        } else {
            pageSize = Math.min(pageSize, MAX_PAGE_SIZE);
        }

        String sortField = ALLOWED_SORT_FIELDS.contains(sortBy) ? sortBy : "id";
        Sort.Direction direction = "DESC".equalsIgnoreCase(sortOrder) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortField));

        Specification<Product> spec = ProductSpecification.filter(filter);

        Page<Product> productPage = productRepository.findAll(spec, pageable);

        List<ProductResponse> products = productPage.getContent()
                .stream()
                .map(productConverter::entityToResponse)
                .toList();

        int totalPages = productPage.getTotalPages();

        List<Integer> navigationPages = calcNavigationPages(page, totalPages, maxNavPage);


        return PaginationResult.<ProductResponse>builder()
                .list(products)
                .currentPage(page)
                .totalPages(totalPages)
                .totalRecords(productPage.getTotalElements())
                .navigationPages(navigationPages)
                .build();
    }

    public List<ProductResponse> getNewProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        return productRepository.findAllByOrderByCreatedAtDesc(pageable).stream().map(productConverter::entityToResponse).toList();
    }

    public List<ProductResponse> getFeatureProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        return productRepository.findAllByIsFeaturedTrueOrderByCreatedAtDesc(pageable).stream().map(productConverter::entityToResponse).toList();
    }

    public ProductResponse getProductBySlug(String slug) {
        Product product = productRepository.findBySlug(slug).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm"));

        return productConverter.entityToResponse(product);
    }

    public List<ProductResponse> getRelatedProducts(String productSlug) {
        Product product = productRepository.findBySlug(productSlug).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm"));
        Pageable pageable = PageRequest.of(0, 4);
        return productRepository.findByCategory_SlugAndSlugNot(product.getCategory().getSlug(), productSlug, pageable).stream().map(productConverter::entityToResponse).toList();
    }

}
