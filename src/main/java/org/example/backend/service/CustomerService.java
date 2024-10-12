package org.example.backend.service;

import org.example.backend.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(String id, CustomerDTO customerDTO);
    void deleteCustomer(String id);
    CustomerDTO getSelectedCustomer(String id);
    List<CustomerDTO> getAllCustomers();
}
