package com.kopach.DTO.impl;

import com.kopach.DTO.DTO;
import com.kopach.domain.Brand;
import com.kopach.domain.Customer;
import com.kopach.exceptions.NoSuchCustomerException;
import com.kopach.exceptions.NoSuchBrandException;
import com.kopach.exceptions.NoSuchTestdriveException;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class CustomerDTO extends DTO<Customer> {
    public CustomerDTO(Customer customer, Link link) throws NoSuchTestdriveException, NoSuchBrandException, NoSuchCustomerException {
        super(customer, link);
    }

    public Long getCustomerId() {
        return getEntity().getId();
    }

    public String getNameOfCustomer() {
        return getEntity().getName_of_customer();
    }

    public String getSurnameOfCustomer() {
        return getEntity().getSurname_of_customer();
    }

    public Brand getBrand() {
        return getEntity().getBrandByBrand();
    }


}
