package com.gaming.cust.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gaming.cust.model.Customer;
import com.gaming.cust.service.CustomerService;
import com.gaming.cust.util.CustomErrorType;

@RestController
@RequestMapping("/restapi")
public class CustomerController {

	public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService; 
	
	// Customer List 
	@RequestMapping(value = "/customer/", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAllCustomers() {
		List<Customer> customers = customerService.findAllCustomers();
		if (customers.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	
	
	// Retrieve Single Customer
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomer(@PathVariable("id") long id) {
		logger.info("Fetching Customer with id {}", id);
		Customer customer = customerService.findById(id);
		if (customer == null) {
			return new ResponseEntity(new CustomErrorType("Customer with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// Create a Customer

	@RequestMapping(value = "/customer/", method = RequestMethod.POST)
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Customer :", customer);

		/*if (customerService.isCustomerExist(customer)) {
			return new ResponseEntity(new CustomErrorType("Unable to create. A Customer with name " + 
			customer.getFirstName() + " already exist."),HttpStatus.CONFLICT);
		}*/
		customerService.saveCustomer(customer);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/customer/{id}").buildAndExpand(customer.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// Update a Customer 

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		logger.info("Updating Customer with id", id);

		Customer currentCustomer = customerService.findById(id);

		if (currentCustomer == null) {
			return new ResponseEntity(new CustomErrorType("Unable to upate. Customer with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentCustomer.setFirstName(customer.getFirstName());
		currentCustomer.setLastName(customer.getLastName());
		currentCustomer.setEmail(customer.getEmail());

		customerService.updateCustomer(currentCustomer);
		return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
	}

	// ------------------- Delete a Customer-----------------------------------------

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Customer with id {}", id);

		Customer customer = customerService.findById(id);
		if (customer == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Customer with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		customerService.deleteCustomerById(id);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

	//- Delete All Customers

	@RequestMapping(value = "/customer/", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteAllCustomers() {
		logger.info("Deleting All Customers");

		customerService.deleteAllCustomers();
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

}