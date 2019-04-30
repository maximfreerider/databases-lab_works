package com.kopach.DTO.impl;

import com.kopach.DTO.DTO;
import com.kopach.controller.CustomerController;
import com.kopach.domain.Brand;
import com.kopach.domain.Customer;
import com.kopach.exceptions.NoSuchCustomerException;
import com.kopach.exceptions.NoSuchBrandException;
import com.kopach.exceptions.NoSuchTestdriveException;
import org.springframework.hateoas.Link;

import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


public class BrandDTO extends DTO<Brand> {
    public BrandDTO(Brand brand, Link link) throws NoSuchBrandException, NoSuchTestdriveException, NoSuchCustomerException {
        super(brand, link);
        add(linkTo(methodOn(CustomerController.class).getCustomerByBrandId(getEntity().getId())).withRel("mobile"));
    }

    public Long getCustomersId() {
        return getEntity().getId();
    }

    public Integer getBrand() {
        return getEntity().getBrand();
    }
  

    public Set<Customer> getCustomers() {
        return getEntity().getCustomer();
    }
}
