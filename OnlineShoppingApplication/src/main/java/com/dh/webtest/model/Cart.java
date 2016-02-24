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
@Table(name="cart")
public class Cart{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int cartId;

	@Column(name = "cost")
	float cost;
	

	@Column(name = "tax")
	float tax;


	@Column(name = "totalcost")
	float totalCost;


	@OneToMany(mappedBy = "cart",  orphanRemoval = true)
	List<CartItem> cartitem;


	@OneToOne(mappedBy = "cart", fetch = FetchType.EAGER, orphanRemoval = true)
	Order order;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	Customer customer;

	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public float getCost() {
		return cost;
	}


	public void setCost(float cost) {
		this.cost = cost;
	}


	public float getTax() {
		return tax;
	}


	public void setTax(float tax) {
		this.tax = tax;
	}


	public float getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}


	public List<CartItem> getCartitem() {
		return cartitem;
	}


	public void setCartitem(List<CartItem> cartitem) {
		this.cartitem = cartitem;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}


