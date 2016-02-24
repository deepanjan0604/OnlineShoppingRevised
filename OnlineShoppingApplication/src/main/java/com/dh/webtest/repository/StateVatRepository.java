package com.dh.webtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dh.webtest.model.StateVat;

public interface StateVatRepository extends JpaRepository<StateVat, Integer>{

	StateVat findBystate(String state);

}





