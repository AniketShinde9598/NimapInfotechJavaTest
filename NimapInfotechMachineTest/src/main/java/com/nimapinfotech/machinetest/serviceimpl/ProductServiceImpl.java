package com.nimapinfotech.machinetest.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nimapinfotech.machinetest.pojo.Category;
import com.nimapinfotech.machinetest.pojo.Product;
import com.nimapinfotech.machinetest.repository.ProductRepository;
import com.nimapinfotech.machinetest.repository.CategoryRepository;
import com.nimapinfotech.machinetest.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Product getProductById(int id) {
		return productRepository.findById(id).
		orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
	}

	@Override
	public Product createProduct(Product product) {
		int categoryId = product.getCategory().getCategory_id();
		 
		Category category = categoryRepository.findById(categoryId) //Validate Category existence
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
		product.setCategory(category);
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(int id, Product product) {
		Product existingProduct= getProductById(id);
		
		int categoryId = product.getCategory().getCategory_id(); //Validate Category existence
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
		
		existingProduct.setProduct_name(product.getProduct_name());
		existingProduct.setProduct_price(product.getProduct_price());
		existingProduct.setCategory(category);
		
		return productRepository.save(existingProduct);
	}

	@Override
	public boolean deleteProduct(int id) {
		
		try {
			Optional<Product> opt = productRepository.findById(id);
			
			if(opt.isPresent()) {
				productRepository.deleteById(id);
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