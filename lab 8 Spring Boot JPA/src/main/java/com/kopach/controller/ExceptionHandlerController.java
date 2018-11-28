package com.kopach.controller;

import com.kopach.DTO.impl.MessageDTO;
import com.kopach.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchBrandException.class)
    ResponseEntity<MessageDTO> handleNoSushCityException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such city not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchTestdriveException.class)
    ResponseEntity<MessageDTO> handleNoSushPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such person not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchCustomerException.class)
    ResponseEntity<MessageDTO> handleNoSushBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such book not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsCustomersForGroupOfCustomerException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForCityException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are persons for this city"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsCustomerForProjectException.class)
    ResponseEntity<MessageDTO> handleExistsBooksForPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are books for this person"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsTestdriveForCustomerException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are persons for this book"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsCustomerInTestdriveException.class)
    ResponseEntity<MessageDTO> handleAlreadyExistsBookInPersonExceptionException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Add imposible. The person already contain this book"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerAbsentException.class)
    ResponseEntity<MessageDTO> handleBookAbsentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Now this book is absent"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TestdriveHasNotCustomerException.class)
    ResponseEntity<MessageDTO> handlePersonHasNotBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("The person hasn't this book"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchLogException.class)
    ResponseEntity<MessageDTO> handleNoSuchLogException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such log not found"), HttpStatus.NOT_FOUND);
    }

}
