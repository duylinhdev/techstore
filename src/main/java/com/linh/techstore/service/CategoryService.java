package com.linh.techstore.service;

import com.linh.techstore.entity.Category;
import com.linh.techstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> listCategories(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return categoryRepository.findAll(pageable).getContent();
    }
}
