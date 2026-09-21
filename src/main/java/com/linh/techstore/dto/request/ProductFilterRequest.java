package com.linh.techstore.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilterRequest {
    private List<String> brandSlugs;
    private List<String> categorySlugs;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String keyword;

}
