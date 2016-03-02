package com.dh.web.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dh.web.model.Cart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="shippingaddresses")
public class ShippingAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int shippingId;

	@Column(name = "address1")
	String address1;
	
	@Column(name = "address2")
	String address2;
	
	@Column(name = "city")
	String city;
	
	
	@Column(name = "state")
	String state;
	
	@Column(name = "pin")
	int pin;
	
	@Column(name = "phone")
	long phone;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	Customer customer;
	
	@JsonIgnore
	@OneToOne(mappedBy = "shippingaddress", orphanRemoval = true)
	Cart cart;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	StateVat stateVat;
	
	@OneToMany(mappedBy = "shippingAddress",  orphanRemoval = true)
	List<Order> order;
	
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	//@JsonIgnore
	public StateVat getStateVat() {
		return stateVat;
	}
	//@JsonProperty(value="stateVat")
	public void setStateVat(StateVat stateVat) {
		this.stateVat = stateVat;
	}

	public int getShippingId() {
		return shippingId;
	}

	public void setShippingId(int shippingId) {
		this.shippingId = shippingId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
	//@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}
	//@JsonProperty(value="customer")
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	

}


