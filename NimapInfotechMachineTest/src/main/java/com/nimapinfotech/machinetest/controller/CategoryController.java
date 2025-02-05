package com.nimapinfotech.machinetest.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nimapinfotech.machinetest.pojo.Category;
import com.nimapinfotech.machinetest.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page){
		return categoryService.getAllCategories(PageRequest.of(page,10));
	}
	
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable int id) {
		return categoryService.getCategoryById(id);
	}
	
	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	
	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable int id , @RequestBody Category category) {
		return categoryService.updateCategory(id, category);
	}
	
	@DeleteMapping("/{id}")
	public HashMap<String, String> deleteCategory(@PathVariable int id) {
		HashMap<String, String> hmap = new HashMap<>();
		if(categoryService.deleteCategory(id))
			hmap.put("message", "Category deleted successfully");
		else
			hmap.put("message", "failure");
		
		return hmap;
	}
}