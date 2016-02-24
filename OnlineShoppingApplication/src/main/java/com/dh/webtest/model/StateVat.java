package com.dh.webtest.model;

import java.util.List;
import java.lang.Object;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="statevat")
public class StateVat{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int stateId;

	@Column(name = "state")
	String state;
	
	@Column(name = "vatpercentage")
	float vatPercent;


	@OneToMany(mappedBy = "stateVat", orphanRemoval = true)
	List<ShippingAddress> shippingAddress;
	
	


	public List<ShippingAddress> getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(List<ShippingAddress> shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}


	public float getVatPercent() {
		return vatPercent;
	}

	public void setVatPercent(float vatPercent) {
		this.vatPercent = vatPercent;
	}
	

}


