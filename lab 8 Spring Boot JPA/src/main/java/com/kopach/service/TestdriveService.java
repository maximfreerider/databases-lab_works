package com.kopach.service;

import com.kopach.Repository.CustomerRepository;
import com.kopach.Repository.TestdriveRepository;
import com.kopach.domain.Customer;
import com.kopach.domain.Testdrive;
import com.kopach.exceptions.ExistsCustomerForTestdriveException;
import com.kopach.exceptions.NoSuchCustomerException;
import com.kopach.exceptions.NoSuchTestdriveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class TestdriveService {
    @Autowired
    TestdriveRepository testdriveRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Set<Testdrive> getLecturersByStudentId(Long customer_id) throws NoSuchCustomerException {
        Customer customer = customerRepository.findById(customer_id).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        return customer.getTestdrives();
    }

    public Testdrive getTestdrive(Long testdrive_id) throws NoSuchTestdriveException {
        Testdrive testdrive = testdriveRepository.findById(testdrive_id).get();//2.0.0.M7
        if (testdrive == null) throw new NoSuchTestdriveException();
        return testdrive;
    }

    public List<Testdrive> getAllTestdrives() {
        return testdriveRepository.findAll();
    }

    @Transactional
    public void createTestdrive(Testdrive testdrive) {
        testdriveRepository.save(testdrive);
    }

    @Transactional
    public void updateTestdrive(Testdrive uTestdrive, Long testdrive_id) throws NoSuchTestdriveException {
        Testdrive testdrive= testdriveRepository.findById(testdrive_id).get();//2.0.0.M7
        if (testdrive == null) throw new NoSuchTestdriveException();
        //update
        testdrive.setName_of_testdrive(uTestdrive.getName_of_testdrive());
    }

    @Transactional
    public void deleteTestdrive(Long testdrive_id) throws NoSuchTestdriveException, ExistsCustomerForTestdriveException {
        Testdrive testdrive = testdriveRepository.findById(testdrive_id).get();//2.0.0.M7

        if (testdrive == null) throw new NoSuchTestdriveException();
        if (testdrive.getMobileSet().size() != 0) throw new ExistsCustomerForTestdriveException();
        testdriveRepository.delete(testdrive);
    }
}
