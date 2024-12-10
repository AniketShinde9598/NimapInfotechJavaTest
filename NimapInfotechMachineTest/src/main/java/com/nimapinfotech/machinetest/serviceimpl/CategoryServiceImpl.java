package com.nimapinfotech.machinetest.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.nimapinfotech.machinetest.pojo.Category;
import com.nimapinfotech.machinetest.repository.CategoryRepository;
import com.nimapinfotech.machinetest.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Page<Category> getAllCategories(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryRepository.findById(id).
		orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
	}

	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(int id, Category category) {
		Category existingCategory= getCategoryById(id);
		existingCategory.setCategory_name(category.getCategory_name());
		return categoryRepository.save(existingCategory);
	}

	@Override
	public boolean deleteCategory(int id) {
		
		try {
			Optional<Category> opt = categoryRepository.findById(id);
			
			if(opt.isPresent()) {
				categoryRepository.deleteById(id);
				return true;
			}
			else
				return false;
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}