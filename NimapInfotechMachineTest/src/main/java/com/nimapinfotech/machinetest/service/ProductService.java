package com.nimapinfotech.machinetest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nimapinfotech.machinetest.pojo.Product;

public interface ProductService {

	Page<Product> getAllProducts(Pageable pageable);
	Product getProductById(int id);
	Product createProduct(Product product);
	Product updateProduct(int id, Product product);
	boolean deleteProduct(int id);
}