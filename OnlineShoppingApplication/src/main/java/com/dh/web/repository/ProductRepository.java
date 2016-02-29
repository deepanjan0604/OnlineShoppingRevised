package com.dh.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dh.web.model.Brand;
import com.dh.web.model.Category;
import com.dh.web.model.Product;

public interface ProductRepository extends JpaRepository<   Product, Integer>{

	

List<Product> findByCategoryCategoryId(int categoryId);

List<Product> findByBrandBrandId(int brandId);

}





