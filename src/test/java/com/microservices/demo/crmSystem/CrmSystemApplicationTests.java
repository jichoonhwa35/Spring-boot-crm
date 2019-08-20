package com.microservices.demo.crmSystem;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservices.crmSystem.CrmSystemApplication;
import com.microservices.crmSystem.entity.Address;
import com.microservices.crmSystem.entity.Customer;
import com.microservices.crmSystem.repository.CustomerRepository;
import com.microservices.crmSystem.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmSystemApplication.class)
public class CrmSystemApplicationTests {

	@Autowired
	private CustomerService service;

	@MockBean
	private CustomerRepository repository;

	List<Customer> customers = new ArrayList<>();
	Customer user1;

	@Before
	public void setUP() {
		Address address1 = new Address("Calton Ave", "Piscataway", "NJ", "USA", "08854");
		Customer user1 = new Customer("orange", "orange", "orange", "orange", "orange@orange.com");
		user1.getAddress().add(address1);
//		Address address2 = new Address("Calton Ave", "Piscataway", "NJ", "USA", "08854");
//		User user2 = new User("onion", "onion", "onion", "onion", "onione@onion.com");
//		user2.getAddress().add(address2);
//		users.add(user1);
//		users.add(user2);
	}

	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Customer("orange", "orange", "orange", "orange", "orange@orange.com"),
						new Customer("onion", "onion", "onion", "onion", "onione@onion.com"))
				.collect(Collectors.toList()));
		assertEquals(2, service.getCustomers().size());
	}

	@Test
	public void getUserbyUsernameTest() {
		String username = "orange";
		when(repository.findByUsername(username)).thenReturn(user1);
		assertEquals(user1, service.getUserbyUsername(username));
	}

	@Test
	public void addUserTest() {
		when(repository.save(user1)).thenReturn(user1);
		assertEquals(user1, service.addCustomer(user1));
	}

	@Test
	public void deleteUserTest() {
		Customer customer = new Customer("orange", "orange", "orange", "orange", "orange@orange.com");
		service.deleteUser(customer);
		verify(repository, times(1)).delete(customer);
	}

}
