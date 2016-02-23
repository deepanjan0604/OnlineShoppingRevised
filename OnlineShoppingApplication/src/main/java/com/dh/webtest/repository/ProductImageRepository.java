package com.dh.webtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dh.webtest.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>{

}
