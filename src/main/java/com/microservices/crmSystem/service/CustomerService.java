package com.microservices.crmSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.crmSystem.entity.Customer;
import com.microservices.crmSystem.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;

	public Customer addCustomer(Customer customer) {
		return repository.save(customer);
	}

	public List<Customer> getCustomers() {
		List<Customer> customers = repository.findAll();
		return customers;
	}

	public Customer getUserById(int id) {
		return repository.findById(id);
	}

	public Customer getUserbyUsername(String username) {
		return repository.findByUsername(username);
	}

	public void deleteUser(Customer customer) {
		repository.delete(customer);
	}

}
