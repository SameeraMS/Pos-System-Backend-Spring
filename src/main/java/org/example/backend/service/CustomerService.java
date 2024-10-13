package org.example.backend.service;

import org.example.backend.dto.CustomerStatus;
import org.example.backend.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(String id, CustomerDTO customerDTO);
    void deleteCustomer(String id);
    CustomerStatus getSelectedCustomer(String id);
    List<CustomerDTO> getAllCustomers();
    String generateCustomerId();
}
