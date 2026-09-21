package com.linh.techstore.util;

import com.linh.techstore.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class ProductImageUtils {

    private static final String DEFAULT_PRODUCT_IMAGE = "default_product_image.jpg";

    public String extractMainUrl(Set<ProductImage> productImages) {
        if (productImages == null || productImages.isEmpty()) {
            return DEFAULT_PRODUCT_IMAGE;
        }

        return productImages.stream().filter(img -> Objects.equals(Boolean.TRUE, img.isPrimary()))
                .map(ProductImage::getImageUrl)
                .findFirst()
                .orElseGet(() -> productImages.iterator().next().getImageUrl());

    }

    public List<String> extractGalleryUrl(Set<ProductImage> images) {
        if (images == null || images.isEmpty()) {
            return List.of(DEFAULT_PRODUCT_IMAGE);
        }

        return images.stream().sorted((img1, img2) -> Boolean.compare(Objects.equals(Boolean.TRUE, img2.isPrimary()), Objects.equals(Boolean.TRUE, img1.isPrimary()))).map(ProductImage::getImageUrl).toList();
    }


}
