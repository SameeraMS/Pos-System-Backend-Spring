package org.example.backend.service.impl;

import org.example.backend.dao.CustomerDao;
import org.example.backend.dto.impl.CustomerDTO;
import org.example.backend.entity.impl.Customer;
import org.example.backend.exception.CustomerNotFoundException;
import org.example.backend.exception.DataPersistException;
import org.example.backend.service.CustomerService;
import org.example.backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = customerDao.save(mapping.toCustomerEntity(customerDTO));
        if (savedCustomer == null) {
            throw new DataPersistException("Customer not saved");
        }
    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {

    }

    @Override
    public void deleteCustomer(String id) {
        if (customerDao.existsById(id)) {
            customerDao.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public CustomerDTO getSelectedCustomer(String id) {
        Optional<Customer> customer = customerDao.findById(id);
        if (customer.isPresent()) {
            return mapping.toCustomerDTO(customerDao.getReferenceById(id));
        } else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.toCustomerDTOList(customerDao.findAll());
    }
}
