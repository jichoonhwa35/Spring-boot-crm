package com.microservices.crmSystem.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.crmSystem.entity.Customer;
import com.microservices.crmSystem.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final static Logger log = LogManager.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	public CustomerController(CustomerService theuserService) {
		customerService = theuserService;
	}

	// GET USERS LIST
	@GetMapping("/all")
	public List<Customer> getCustomers() {
		log.info("get all users successfully!");
		return customerService.getCustomers();
	}

	// GET USER BY ID
	@GetMapping("/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer customer = customerService.getUserById(customerId);

		if (customer == null) {
			log.warn("User is not found");
			throw new RuntimeException("User is not found!");
		}

		log.info("Get the user successfully!");
		return customer;
	}

//	@GetMapping("/{username}")
//	public Customer getCustomer(@PathVariable String username) {
//
//		Customer customer = customerService.getUserbyUsername(username);
//
//		if (customer == null) {
//			log.warn("User is not found");
//			throw new RuntimeException("User is not found!");
//		}
//
//		log.info("Get the user successfully!");
//		return customer;
//	}

	// ADD USER
	@PostMapping("/add")
	public String addCustomer(@RequestBody Customer customer) {

		// FORCE TO SAVE NEW ITEM INSTEAD OF UPDATE
		customer.setId(0);
		customerService.addCustomer(customer);
		log.info("User added successfully");
		return "user added successfully!";
	}

	// UPDATE USER
	@PutMapping("/edit")
	public Customer updateCusotmer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return customer;
	}

	// DELETE USER
	@DeleteMapping("/delete/{customerId}")
	public List<Customer> deleteCustomer(@PathVariable int customerId) {

		Customer customer = customerService.getUserById(customerId);

		if (customer == null) {
			log.warn("User is not found!");
			throw new RuntimeException("User is not found!");
		}

		customerService.deleteUser(customer);
		log.info("user deleted successfully");
		return customerService.getCustomers();
	}

}
