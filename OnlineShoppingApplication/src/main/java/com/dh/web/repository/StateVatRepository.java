package com.dh.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dh.web.model.StateVat;

public interface StateVatRepository extends JpaRepository<StateVat, Integer>{

	StateVat findBystate(String state);

}





