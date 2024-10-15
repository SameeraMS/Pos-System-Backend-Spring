package org.example.backend.controller;


import org.example.backend.dto.impl.CustomerDTO;
import org.example.backend.exception.CustomerNotFoundException;
import org.example.backend.exception.DataPersistException;
import org.example.backend.service.CustomerService;
import org.example.backend.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/nextId")
    public String nextCustomerId() {
        return customerService.generateCustomerId();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerDTO customerDto) {
        try {
            boolean isContactValid = customerDto.getContact().matches(Regex.CONTACT_REGEX);
            if (isContactValid) {
                customerService.saveCustomer(customerDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/{propertyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getCustomer(@PathVariable("propertyId") String propertyId) {
        return customerService.searchByContact(propertyId);

    }

    @PutMapping(value = "/{propertyId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("propertyId") String propertyId, @RequestBody CustomerDTO customerDto) {
        boolean isCustomerIdValid = propertyId.matches(Regex.CUSTOMER_ID_REGEX);
        try {
            if(isCustomerIdValid){
                customerService.updateCustomer(propertyId, customerDto);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (CustomerNotFoundException | DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{propertyId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("propertyId") String propertyId) {
        boolean isCustomerIdValid = propertyId.matches(Regex.CUSTOMER_ID_REGEX);
        try {
            if(isCustomerIdValid){
                customerService.deleteCustomer(propertyId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (CustomerNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
