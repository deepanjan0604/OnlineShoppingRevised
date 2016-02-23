package com.dh.webtest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.jdbc.Blob;

@Entity
@Table(name="brands")
public class Brand {
	
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	int brandId;
	
	@Id
	@Column(name = "brandname")
	String brandName;

	@OneToMany(mappedBy = "brand",  orphanRemoval = true)
	@JsonIgnore
	List<Product> product;
	
	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	
	
}


