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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.jdbc.Blob;

@Entity
@Table(name="categories")
public class Category {
	
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	int categoryId;

	@Id
	@Column(name = "categoryname")
	String categoryName;
	

	@OneToMany(mappedBy = "category",  orphanRemoval = true)
	@JsonIgnore
	List<Product> product;


	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public List<Product> getProduct() {
		return product;
	}
	
	public void setProduct(List<Product> product) {
		this.product = product;
	}


	
	
}


