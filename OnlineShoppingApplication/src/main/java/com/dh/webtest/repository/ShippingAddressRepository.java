package com.dh.webtest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dh.webtest.model.Customer;
import com.dh.webtest.model.ShippingAddress;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer>{

	List<ShippingAddress> findByCustomer(Customer customer);


}





