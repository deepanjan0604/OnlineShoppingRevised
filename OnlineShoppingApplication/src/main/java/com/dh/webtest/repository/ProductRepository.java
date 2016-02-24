package com.dh.webtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dh.webtest.model.Product;

public interface ProductRepository extends JpaRepository<   Product, Integer>{

	//Product findByproductId(int productId);


}





