package com.nimapinfotech.machinetest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nimapinfotech.machinetest.pojo.Category;

public interface CategoryService {

	Page<Category> getAllCategories(Pageable pageable);
	Category getCategoryById(int id);
	Category createCategory(Category category);
	Category updateCategory(int id, Category category);
	boolean deleteCategory(int id);
}