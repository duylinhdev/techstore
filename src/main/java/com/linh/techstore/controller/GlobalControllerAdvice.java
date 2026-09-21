package com.linh.techstore.controller;

import com.linh.techstore.entity.Category;
import com.linh.techstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> addCategoriesToModel() {
        return categoryService.listCategories(10);
    }
}
