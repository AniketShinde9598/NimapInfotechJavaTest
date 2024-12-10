package com.nimapinfotech.machinetest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nimapinfotech.machinetest.pojo.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}