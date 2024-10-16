package org.example.backend.controller;


import org.example.backend.dto.CustomerStatus;
import org.example.backend.dto.impl.CustomerDTO;
import org.example.backend.exception.CustomerNotFoundException;
import org.example.backend.exception.DataPersistException;
import org.example.backend.service.CustomerService;
import org.example.backend.util.Regex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

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
                logger.error("Faild with contact: ",customerDto.getContact());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (DataPersistException e) {
            e.printStackTrace();
            logger.error("Faild with: ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            logger.error("Faild with: ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Faild with: ",e.getMessage());
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

    @GetMapping(value = "/search/{propertyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerStatus getSelectedCustomer(@PathVariable("propertyId") String propertyId) {
        return customerService.getSelectedCustomer(propertyId);
    }

    @PutMapping(value = "/{propertyId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("propertyId") String propertyId, @RequestBody CustomerDTO customerDto) {
        boolean isCustomerIdValid = propertyId.matches(Regex.CUSTOMER_ID_REGEX);
        try {
            if(isCustomerIdValid){
                customerService.updateCustomer(propertyId, customerDto);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                logger.error("Faild with customer id: ",propertyId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (CustomerNotFoundException | DataPersistException e){
            e.printStackTrace();
            logger.error("Faild with: ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Faild with: ",e.getMessage());
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
                logger.error("Faild with customer id: ",propertyId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (CustomerNotFoundException e){
            e.printStackTrace();
            logger.error("Faild with: ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Faild with: ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
