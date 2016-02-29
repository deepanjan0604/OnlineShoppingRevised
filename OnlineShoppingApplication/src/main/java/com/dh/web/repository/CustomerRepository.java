package com.dh.web.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dh.web.model.Customer;

public interface CustomerRepository extends JpaRepository<  Customer, Integer>{

	public List<Customer> customerlist= new ArrayList<Customer>();

	Customer findByuserName(String userName);
}





