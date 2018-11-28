package com.kopach.Repository;

import com.kopach.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
