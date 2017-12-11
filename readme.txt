Info 
======
This code consists of API for Customer CRUD Operations


Install 
==========

expand the zip
set path for java, maven etc
(Optional) Open project in eclipse 

Run from command prompt 
=======================
Open one window (command prompt) 
	go to folder CustomerCrud
	mvn install -Dmaven.test.skip=true     (optional for compilation)
	mvn spring-boot:run

Open another window
	go to folder CustomerCrud
	mvn test 


Run From Postman 
================
List all customers GET http://localhost:8080/CustomerCrud/restapi/customer/
get customer 1     GET http://localhost:8080/CustomerCrud/restapi/customer/1



List all customers GET http://54.200.145.41:8080/CustomerCrud/restapi/customer/
get customer 1     GET http://54.200.145.41:8080/CustomerCrud/restapi/customer/1






create customer    POST http://localhost:8080/CustomerCrud/api/customer
		   {
			firstName: "",
			lastName: "",
			email:a1@gmail.com
		   }
		raw 	application/json 


Update customer    PUT http://localhost:8080/CustomerCrud/api/customer/3
		   {
			firstName: "sandy",
			lastName: "test",
			email:sandy1@gmail.com
		   }