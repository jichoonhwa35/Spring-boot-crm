package com.microservices.crmSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.crmSystem.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findById(int theId);

	public Customer findByUsername(String username);
}
