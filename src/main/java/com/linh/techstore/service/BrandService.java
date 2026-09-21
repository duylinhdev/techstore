package com.linh.techstore.service;

import com.linh.techstore.entity.Brand;
import com.linh.techstore.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<Brand> listBrands() {
        Pageable pageable = PageRequest.of(0, 10);
        return brandRepository.findAll(pageable).getContent();
    }

    public List<Brand> getBrandsByCategorySlug(String categorySlug) {
        Pageable pageable = PageRequest.of(0, 10);

        if (categorySlug != null && !categorySlug.isEmpty()) {
            return brandRepository.findBrandsByCategorySlug(categorySlug, pageable);
        }
        return brandRepository.findAll(pageable).getContent();

    }
}
