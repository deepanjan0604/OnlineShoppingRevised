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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.jdbc.Blob;

@Entity
@Table(name="cartitems")
public class CartItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int cartitemId;

	@Column(name = "quantity")
	int quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	Cart cart;

	@ManyToOne(fetch = FetchType.EAGER)
	//@JsonIgnore
	Product product;
	
	public int getCartitemId() {
		return cartitemId;
	}

	public void setCartitemId(int cartitemId) {
		this.cartitemId = cartitemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public float getInitialprice(){
		float price = getProduct().productPrice;
		int quantity = getQuantity();
		float total = price*quantity;
		return total;
	}
	}
	



