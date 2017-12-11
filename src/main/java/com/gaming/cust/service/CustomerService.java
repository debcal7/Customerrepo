package com.gaming.cust.service;


import java.util.List;

import com.gaming.cust.model.Customer;

public interface CustomerService {
	
	Customer findById(long id);
	
	Customer findByName(String name);
	
	void saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomerById(long id);

	List<Customer> findAllCustomers();
	
	void deleteAllCustomers();
	
	boolean isCustomerExist(Customer customer);
	
}
