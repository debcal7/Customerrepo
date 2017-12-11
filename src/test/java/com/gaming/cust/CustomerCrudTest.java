package com.gaming.cust;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.gaming.cust.model.Customer;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomerCrudTest {
	
	//public final String REST_SERVICE_URI = "http://localhost:8080/CustomerCrud/restapi";
	public final String REST_SERVICE_URI = "http://54.200.145.41:8080/CustomerCrud/restapi";

	@Test
	public void listAllCustomers() {
		System.out.println("Testing listAllCustomers API-----------");
		List<LinkedHashMap<String, Object>> customersMap = null;
		RestTemplate restTemplate = new RestTemplate();
		for (int k=0;k<5000;k++){
			customersMap = restTemplate.getForObject(REST_SERVICE_URI + "/customer/",List.class);
		}
			

		if (customersMap != null) {
			for (LinkedHashMap<String, Object> map : customersMap) {
				System.out.println("Customer : id=" + map.get("id") + ", firstName=" + map.get("firstName")
						+ ", lastname=" + map.get("lastName") + ", email=" + map.get("email"));
				;
			}
		} 
	}
	
	 /* GET */
	@Test
    public  void getCustomer(){
        System.out.println("\n\nTesting getCustomer API----------");
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = restTemplate.getForObject(REST_SERVICE_URI+"/customer/1", Customer.class);
        System.out.println(customer);
    }
     
    /* POST */
	@Test
    public  void createCustomer() {
        System.out.println("\n\nTesting create Customer API----------");
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = new Customer(0,"Sarah","jones","sarah@email.com");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/customer/", customer, Customer.class);
        System.out.println("Location : "+uri.toASCIIString());
       
    }
 
    /* PUT */
	@Test
    public  void updateCustomer() {
        System.out.println("\n\nTesting update Customer API----------");
        RestTemplate restTemplate = new RestTemplate();
        Customer customer  = new Customer(1,"Tony","david","a1@email.com");
        restTemplate.put(REST_SERVICE_URI+"/customer/1", customer);
        System.out.println("Customer="+customer.toString());
    }
 
    /* DELETE */
	@Test
    public  void deleteCustomer() {
        System.out.println("\n\nTesting delete Customer API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/customer/3");
      
    }
 
     /* DELETE */
	//@Test
    public  void deleteAllCustomers() {
        System.out.println("Testing all delete Customers API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/customer/");
    }

}