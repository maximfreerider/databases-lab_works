package com.kopach.service;

import com.kopach.Repository.BrandRepository;
import com.kopach.Repository.CustomerRepository;
import com.kopach.Repository.TestdriveRepository;
import com.kopach.domain.Brand;
import com.kopach.domain.Customer;
import com.kopach.domain.Testdrive;
import com.kopach.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    TestdriveRepository testdriveRepository;

    public Set<Customer> getCustomerByBrandId(Long customer_id) throws NoSuchBrandException {
        Brand brand = brandRepository.findById(customer_id).get();//2.0.0.M7
        if (brand == null) throw new NoSuchBrandException();
        return brand.getCustomer();
    }

    public Customer getCustomers(Long customer_id) throws NoSuchCustomerException {
        Customer customer = customerRepository.findById(customer_id).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        return customer;
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Set<Customer> getCustomerByTesdriveId(Long customer_id) throws NoSuchTestdriveException {
        Testdrive testdrive = testdriveRepository.findById(customer_id).get();//2.0.0.M7
        if (testdrive == null) throw new NoSuchTestdriveException();
        return testdrive.getMobileSet();
    }

    @Transactional
    public void createCustomer(Customer customer, Long brand_id) throws NoSuchBrandException {
        if (brand_id > 0) {
            Brand brand = brandRepository.findById(brand_id).get();//2.0.0.M7
            if (brand == null) throw new NoSuchBrandException();
            customer.setBrandByBrand(brand);
        }
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(Customer uCustomer, Long customer_id, Long customer_id_1) throws NoSuchBrandException, NoSuchCustomerException {
        Brand brand = brandRepository.findById(customer_id_1).get();//2.0.0.M7
        if (customer_id_1 > 0) {
            if (brand == null) throw new NoSuchBrandException();
        }
        Customer customer = customerRepository.findById(customer_id).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        customer.setName_of_customer(uCustomer.getName_of_customer());
        customer.setSurname_of_customer(uCustomer.getSurname_of_customer());
        if (customer_id_1 > 0) customer.setBrandByBrand(brand);
        else customer.setBrandByBrand(null);
        customerRepository.save(customer);
    }

    @Transactional
    public void deleteCustomer(Long customer_id) throws NoSuchCustomerException, ExistsTestdriveForCustomerException {
        Customer customer = customerRepository.findById(customer_id).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        if (customer.getTestdrives().size() != 0) throw new ExistsTestdriveForCustomerException();
        customerRepository.delete(customer);
    }

    @Transactional
    public void addTestdriveForCustomer(Long customer_id, Long testdrive_id)
            throws NoSuchCustomerException, NoSuchTestdriveException, AlreadyExistsTestdriveInCustomerException, TestdriveAbsentException {
        Customer customer = customerRepository.findById(customer_id).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        Testdrive testdrive = testdriveRepository.findById(testdrive_id).get();//2.0.0.M7
        if (testdrive == null) throw new NoSuchTestdriveException();
        if (customer.getTestdrives().contains(testdrive) == true) throw new AlreadyExistsTestdriveInCustomerException();
        customer.getTestdrives().add(testdrive);
        customerRepository.save(customer);
    }

    @Transactional
    public void removeTestdriveForCustomer(Long customer_id, Long testdrive_id)
            throws NoSuchCustomerException, NoSuchTestdriveException, CustomerHasNotTestdriveException {
        Customer customer = customerRepository.findById(customer_id).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        Testdrive testdrive= testdriveRepository.findById(testdrive_id).get();//2.0.0.M7
        if (testdrive == null) throw new NoSuchTestdriveException();
        if (customer.getTestdrives().contains(testdrive) == false) throw new CustomerHasNotTestdriveException();
        customer.getTestdrives().remove(testdrive);
        customerRepository.save(customer);
    }
}
