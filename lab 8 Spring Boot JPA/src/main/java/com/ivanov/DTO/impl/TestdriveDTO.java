package com.kopach.DTO.impl;

import com.kopach.DTO.DTO;
import com.kopach.domain.Testdrive;
import com.kopach.exceptions.NoSuchTestdriveException;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class TestdriveDTO extends DTO<Testdrive> {
    public TestdriveDTO(Testdrive testdrive, Link link) throws NoSuchTestdriveException {
        super(testdrive, link);
    }

    public Long getTestdriveId() {
        return getEntity().getId();
    }

    public String getNameOfTestdrive() {
        return getEntity().getName_of_testdrive();
    }

    public String getSurnameOfTestdrive() {
        return getEntity().getSurname_of_testdrive();
    }
}
