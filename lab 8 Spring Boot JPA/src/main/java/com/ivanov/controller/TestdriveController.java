package com.kopach.controller;

import com.kopach.DTO.impl.TestdriveDTO;
import com.kopach.domain.Testdrive;
import com.kopach.exceptions.ExistsCustomerForTestdriveException;
import com.kopach.exceptions.NoSuchCustomerException;
import com.kopach.exceptions.NoSuchTestdriveException;
import com.kopach.service.TestdriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class TestdriveController {
    @Autowired
    TestdriveService testdriveService;

    @GetMapping(value = "/api/testdrive/Customer/{customer_id}")
    public ResponseEntity<Set<TestdriveDTO>> getTestdriveByCustomerID(@PathVariable Long student_id) throws NoSuchCustomerException, NoSuchTestdriveException {
        Set<Testdrive> testdrives = testdriveService.getLecturersByStudentId(student_id);
        Link link = linkTo(methodOn(TestdriveController.class).getAllTestdrives()).withSelfRel();

        Set<TestdriveDTO> testdriveDTOS = new HashSet<>();
        for (Testdrive entity : testdrives) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            TestdriveDTO dto = new TestdriveDTO(entity, selfLink);
            testdriveDTOS.add(dto);
        }

        return new ResponseEntity<>(testdriveDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/testdrive/{testdrive_id}")
    public ResponseEntity<TestdriveDTO> getTestdrive(@PathVariable Long testdrive_id) throws NoSuchTestdriveException, NoSuchCustomerException {
        Testdrive testdrive = testdriveService.getTestdrive(testdrive_id);
        Link link = linkTo(methodOn(TestdriveController.class).getTestdrive(testdrive_id)).withSelfRel();

        TestdriveDTO testdriveDTO = new TestdriveDTO(testdrive, link);

        return new ResponseEntity<>(testdriveDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/testdrive")
    public ResponseEntity<Set<TestdriveDTO>> getAllTestdrives() throws NoSuchTestdriveException, NoSuchCustomerException {
        List<Testdrive> testdrives = testdriveService.getAllTestdrives();
        Link link = linkTo(methodOn(TestdriveController.class).getAllTestdrives()).withSelfRel();

        Set<TestdriveDTO> testdriveDTOS = new HashSet<>();
        for (Testdrive entity : testdrives) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            TestdriveDTO dto = new TestdriveDTO(entity, selfLink);
            testdriveDTOS.add(dto);
        }

        return new ResponseEntity<>(testdriveDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/api/testdrive")
    public ResponseEntity<TestdriveDTO> addTestdrive(@RequestBody Testdrive testdrive) throws NoSuchTestdriveException, NoSuchCustomerException {
        testdriveService.createTestdrive(testdrive);
        Link link = linkTo(methodOn(TestdriveController.class).getTestdrive(testdrive.getId())).withSelfRel();

        TestdriveDTO testdriveDTO = new TestdriveDTO(testdrive, link);

        return new ResponseEntity<>(testdriveDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/testdrive/{testdrive_id}")
    public ResponseEntity<TestdriveDTO> updateTestdrive(@RequestBody Testdrive uCustomer, @PathVariable Long testdrive_id) throws NoSuchTestdriveException, NoSuchCustomerException {
        testdriveService.updateTestdrive(uCustomer, testdrive_id);
        Testdrive testdrive = testdriveService.getTestdrive(testdrive_id);
        Link link = linkTo(methodOn(TestdriveController.class).getTestdrive(testdrive_id)).withSelfRel();

        TestdriveDTO testdriveDTO = new TestdriveDTO(testdrive, link);

        return new ResponseEntity<>(testdriveDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/testdrive/{testdrive_id}")
    public  ResponseEntity deleteTestdrive(@PathVariable Long testdrive_id) throws ExistsCustomerForTestdriveException, NoSuchTestdriveException {
        testdriveService.deleteTestdrive(testdrive_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
