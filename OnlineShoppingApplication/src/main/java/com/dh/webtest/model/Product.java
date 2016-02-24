package com.dh.webtest.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.jdbc.Blob;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int productId;

	@Column(name = "name")
	String productName;
	
	@Column(name = "price")
	float productPrice;
	
	@Column(name = "quantity")
	int quantity;
	
	@Basic(fetch = FetchType.LAZY)
	@Column(name="image")
	@Lob
    private String image;
	
	
	 @Column(name="extn")
	 String extn;
	
	
	@OneToMany(mappedBy = "product",  orphanRemoval = true)
	List<ProductImage> productimage;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	Brand brand;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	
	Category category;

	@OneToMany(mappedBy = "product",  orphanRemoval = true)
	List<CartItem> cartitem;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	


	public List<ProductImage> getProductimage() {
		return productimage;
	}

	public void setProductimage(List<ProductImage> productimage) {
		this.productimage = productimage;
	}
	//@JsonIgnore
	public Brand getBrand() {
		return brand;
	}
	@JsonProperty("brand")
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	// @JsonIgnore
	public Category getCategory() {
		return category;
	}
	 // @JsonProperty("category")
	public void setCategory(Category category) {
		this.category = category;
	}

	public List<CartItem> getCartitem() {
		return cartitem;
	}

	public void setCartitem(List<CartItem> cartitem) {
		this.cartitem = cartitem;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getExtn() {
		return extn;
	}

	public void setExtn(String extn) {
		this.extn = extn;
	}

	
	

}


