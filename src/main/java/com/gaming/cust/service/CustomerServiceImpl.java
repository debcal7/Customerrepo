package com.gaming.cust.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.gaming.cust.model.Customer;



@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Customer> customers;
	
	static{
		customers= populateDummyCustomers();
	}

	public List<Customer> findAllCustomers() {
		return customers;
	}
	
	public Customer findById(long id) {
		for(Customer customer : customers){
			if(customer.getId() == id){
				return customer;
			}
		}
		return null;
	}
	
	public Customer findByName(String name) {
		for(Customer customer : customers){
			if(customer.getFirstName().equalsIgnoreCase(name)){
				return customer;
			}
		}
		return null;
	}
	
	public void saveCustomer(Customer customer) {
		customer.setId(counter.incrementAndGet());
		customers.add(customer);
	}

	public void updateCustomer(Customer Customer) {
		int index = customers.indexOf(Customer);
		customers.set(index, Customer);
	}

	public void deleteCustomerById(long id) {
		
		for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext(); ) {
		    Customer customer = iterator.next();
		    if (customer.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isCustomerExist(Customer customer) {
		return findByName(customer.getFirstName())!=null;
	}
	
	public void deleteAllCustomers(){
		customers.clear();
	}

	private static List<Customer> populateDummyCustomers(){
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(counter.incrementAndGet(),"Albert","Green", "albert@gmail.com"));
		customers.add(new Customer(counter.incrementAndGet(),"Brad","Sanders", "brad@gmail.com"));
		customers.add(new Customer(counter.incrementAndGet(),"Charles","Anderson", "Charles@gmail.com"));
		customers.add(new Customer(counter.incrementAndGet(),"David","Parker", "david@gmail.com"));
		return customers;
	}

	
}
