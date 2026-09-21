package com.linh.techstore.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private String categoryName;
    private String brandName;
    private String slug;
    private boolean isFeatured;

    private String mainImageUrl;
    private List<String> galleryUrls;

}
