package com.nimapinfotech.machinetest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nimapinfotech.machinetest.pojo.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}