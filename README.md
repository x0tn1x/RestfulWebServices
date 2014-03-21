RestfulWebServices
==================

Java RESTful Web services for a medical system using JAX-RS and Jersey and MySQL DBMS.

Assignment Description:
=======================

This is an individual assignment. In this assignment, you will create Java RESTful Web services for a medical system. Use Oracle WebLogic Server for creating and publishing your web services. Write web services in Java and annotations using JAX-RS and Jersey. The server stores data in MySQL DBMS.

You are welcome to adopt the ORM (Object Relational Mapping) technique in this assignment, but it is not required for this assignment. Create database and populate relations with a reasonable amount of data in MySQL. You need to explicitly describe foreign keys as necessary in relation schemas.

Write DAO class(es) in a way that database connection and JDBC statements are hidden from a web service method. That is, a web service method is implemented by calling DAO methods.

Required Web Services

    To get all doctor's information
    To get all patient's information
    To get all the patients of a doctor.
    To get doctor information by specialty
    To update a doctor information means "to add a new doctor info"
    To update a patient information means "to add a new patient info"
    To delete a doctor info by id
    To find a doctory by id and modify his/her info.
    To find a patient by id and modify his medical record. 

Testing Web Services
You may test your web services using a Chrome browser, but its capability is limited to GET and POST methods. Therefore, you want to use a tool to test your Restful Service API. Here I am suggesting several tools for you to choose:

    cRUL: a command line utility for transferring data with URL syntax to test your restful services. Here is a simple instruction to set up cRUL on Window 7.
    REST Console: https://chrome.google.com/webstore/detail/rest-console/cokgbflfommojglbmbpenpphppikmonn?hl=en
    POSTMAN: http://www.getpostman.com/ 

Deliverables

    RestfulDAO.java containing DAO classes.
    Restful.java files containing all web services.
    web.xml
    Screenshots: Results of each test case from the tool (cRUL, REST Console, POSTMAN, etc) of your choice. For POST and DELETE operations, show me the snapshot of the database after the operation is done. 
