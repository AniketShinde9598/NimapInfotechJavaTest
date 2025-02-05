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
import com.nimapinfotech.machinetest.pojo.Product;
import com.nimapinfotech.machinetest.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page){
		return productService.getAllProducts(PageRequest.of(page,10));
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}
	
	@DeleteMapping("/{id}")
	public HashMap<String, String> deleteProduct(@PathVariable int id) {
		HashMap<String, String> hmap = new HashMap<>();
		if(productService.deleteProduct(id))
			hmap.put("message", "Product deleted successfully");
		else
			hmap.put("message", "failure");
		
		return hmap;
	}
}