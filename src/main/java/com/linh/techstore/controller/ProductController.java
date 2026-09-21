package com.linh.techstore.controller;

import com.linh.techstore.dto.request.ProductFilterRequest;
import com.linh.techstore.dto.response.PaginationResult;
import com.linh.techstore.dto.response.ProductResponse;
import com.linh.techstore.service.BrandService;
import com.linh.techstore.service.CategoryService;
import com.linh.techstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/san-pham")
    public String listProducts(
            @ModelAttribute ProductFilterRequest filter,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "desc") String sortOrder,
            Model model
    ) {
        int maxNavPage = 5;
        PaginationResult<ProductResponse> result = productService.getProductsWithPagination(filter, page, pageSize, sortBy, sortOrder, maxNavPage);
        var categories = categoryService.listCategories(10);
        String selectedCategorySlug = null;
        if (filter.getCategorySlugs() != null && !filter.getCategorySlugs().isEmpty()) {
            selectedCategorySlug = filter.getCategorySlugs().get(0);
        }

        var brands = (selectedCategorySlug != null)
                ? brandService.getBrandsByCategorySlug(selectedCategorySlug)
                : brandService.listBrands();
        model.addAttribute("result", result);
        model.addAttribute("filter", filter);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);

        return "shop";
    }

    @GetMapping("/san-pham/{slug}")
    public String viewProductDetail(@PathVariable String slug, Model model) {
        ProductResponse product = productService.getProductBySlug(slug);

        List<ProductResponse> relatedProducts = productService.getRelatedProducts(slug);

        model.addAttribute("product", product);
        model.addAttribute("relatedProducts", relatedProducts);

        return "product-detail";
    }

}
