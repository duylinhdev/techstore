package com.linh.techstore.converter;

import com.linh.techstore.dto.response.ProductResponse;
import com.linh.techstore.entity.Product;
import com.linh.techstore.util.ProductImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final ProductImageUtils productImageUtils;

    public ProductResponse entityToResponse(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .slug(product.getSlug())
                .isFeatured(product.isFeatured())
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .brandName(product.getBrand() != null ? product.getBrand().getName() : null)
                .mainImageUrl(productImageUtils.extractMainUrl(product.getImages()))
                .galleryUrls(productImageUtils.extractGalleryUrl(product.getImages()))
                .build();
    }
}
