package com.kopach.controller;

import com.kopach.DTO.impl.CustomerDTO;
import com.kopach.domain.Customer;
import com.kopach.exceptions.*;
import com.kopach.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
// get Customer by class id
    @GetMapping(value = "/api/customer/brand/{brand_id}")
    public ResponseEntity<List<CustomerDTO>> getCustomerByBrandId(@PathVariable Long brand_id) throws NoSuchBrandException, NoSuchCustomerException, NoSuchTestdriveException {
        Set<Customer> customers= customerService.getCustomerByBrandId(brand_id);

        Link link = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();

        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer entity : customers) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CustomerDTO dto = new CustomerDTO(entity, selfLink);
            customerDTOS.add(dto);
        }

        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }
// get Customer
    @GetMapping(value = "/api/customer/{customer_id}")
    public ResponseEntity<CustomerDTO> getCustomers(@PathVariable Long customer_id) throws NoSuchCustomerException, NoSuchTestdriveException, NoSuchBrandException {
        Customer customer = customerService.getCustomers(customer_id);
        Link link = linkTo(methodOn(CustomerController.class).getCustomers(customer_id)).withSelfRel();

        CustomerDTO customerDTO = new CustomerDTO(customer, link);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/customer")
    public ResponseEntity<Set<CustomerDTO>> getAllCustomers() throws NoSuchCustomerException, NoSuchTestdriveException, NoSuchBrandException {
        List<Customer> customers = customerService.getAllCustomer();
        Link link = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();

        Set<CustomerDTO> customerDTOS = new HashSet<>();
        for (Customer entity : customers) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CustomerDTO dto = new CustomerDTO(entity, selfLink);
            customerDTOS.add(dto);
        }

        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/customer/testdrive/{testdrive_id}")
    public ResponseEntity<Set<CustomerDTO>> getCustomerByTestdriveID(@PathVariable Long Lecturer_id) throws NoSuchTestdriveException, NoSuchCustomerException, NoSuchBrandException {
        Set<Customer> customers = customerService.getCustomerByTesdriveId(Lecturer_id);
        Link link = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();

        Set<CustomerDTO> customerDTOS = new HashSet<>();
        for (Customer entity : customers) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CustomerDTO dto = new CustomerDTO(entity, selfLink);
            customerDTOS.add(dto);
        }

        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }
// add Customer
    @PostMapping(value = "/api/customer/brand/{brand_id}")
    public  ResponseEntity<CustomerDTO> addCustomer(@RequestBody Customer customer, @PathVariable Long brand_id)
            throws NoSuchBrandException, NoSuchCustomerException, NoSuchTestdriveException {
        customerService.createCustomer(customer, brand_id);
        Link link = linkTo(methodOn(CustomerController.class).getCustomers(customer.getId())).withSelfRel();

        CustomerDTO customerDTO = new CustomerDTO(customer, link);

        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }
//update Customer
    @PutMapping(value = "/api/customer/{customer_id}/brand/{brand_id}")
    public  ResponseEntity<CustomerDTO> updateCustomer(@RequestBody Customer customer,
                                                       @PathVariable Long customer_id, @PathVariable Long brand_id)
            throws NoSuchBrandException, NoSuchCustomerException, NoSuchTestdriveException {
        customerService.updateCustomer(customer, customer_id, brand_id);
        Customer customer1 = customerService.getCustomers(customer_id);
        Link link = linkTo(methodOn(CustomerController.class).getCustomers(customer_id)).withSelfRel();

        CustomerDTO customerDTO = new CustomerDTO(customer1, link);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/customer/{customer_id}")
    public  ResponseEntity deleteCustomer(@PathVariable Long customer_id) throws NoSuchCustomerException, ExistsTestdriveForCustomerException, ExistsTestdriveForCustomerException {
        customerService.deleteCustomer(customer_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/customer/{customer_id}/testdrive/{testdrive_id}")
    public  ResponseEntity<CustomerDTO> addTestdriveForCustomer(@PathVariable Long student_id, @PathVariable Long lecturer_id)
            throws NoSuchCustomerException, NoSuchTestdriveException, NoSuchBrandException, AlreadyExistsTestdriveInCustomerException, TestdriveAbsentException {
        customerService.addTestdriveForCustomer(student_id,lecturer_id);
        Customer customer = customerService.getCustomers(student_id);
        Link link = linkTo(methodOn(CustomerController.class).getCustomers(student_id)).withSelfRel();

        CustomerDTO customerDTO = new CustomerDTO(customer, link);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/customer/{customer_id}/{testdrive_id}")
    public  ResponseEntity<CustomerDTO> removeTestdriveForCustomer(@PathVariable Long customer_id, @PathVariable Long testdrive_id)
            throws NoSuchCustomerException, NoSuchTestdriveException, NoSuchBrandException, CustomerHasNotTestdriveException {
        customerService.removeTestdriveForCustomer(customer_id,testdrive_id);
        Customer customer = customerService.getCustomers(customer_id);
        Link link = linkTo(methodOn(CustomerController.class).getCustomers(testdrive_id)).withSelfRel();

        CustomerDTO customerDTO = new CustomerDTO(customer, link);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

}

