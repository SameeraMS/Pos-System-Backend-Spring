package org.example.backend.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.backend.dao.CustomerDao;
import org.example.backend.dto.CustomerStatus;
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
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = customerDao.save(mapping.toCustomerEntity(customerDTO));
        if (savedCustomer == null) {
            throw new DataPersistException("Customer not saved");
        }
    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {
            Customer customer = customerDao.getReferenceById(id);
            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found");
            }
            customer.setName(customerDTO.getName());
            customer.setContact(customerDTO.getContact());
            customer.setAddress(customerDTO.getAddress());
            customerDao.save(customer);
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
    public CustomerStatus getSelectedCustomer(String id) {
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

    public String generateCustomerId() {
        String jpql = "SELECT c.id FROM Customer c ORDER BY c.id DESC LIMIT 1";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);

        String maxCustomerId = query.getSingleResult();

        if (maxCustomerId != null) {
            int newCustomerId = Integer.parseInt(maxCustomerId.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
}
