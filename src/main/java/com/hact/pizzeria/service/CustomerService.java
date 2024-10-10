package com.hact.pizzeria.service;

import com.hact.pizzeria.persistence.entity.CustomerEntity;
import com.hact.pizzeria.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity getByPhone(String phone) {
        return this.customerRepository.findByPhone(phone);
    }
}
