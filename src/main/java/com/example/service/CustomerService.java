package com.example.service;

import com.example.dto.*;
import com.example.entity.CustomerEntity;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.mapper.CustomerMapper;
import com.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerFindAll> findAll() {
        return customerRepository.findAllByEnabledIsTrueOrderByIdCustomerDesc().stream().map(CustomerMapper::toCustomerFindAll).toList();
    }

    public CustomerCreated create(CustomerCreate dto){
        if (customerRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())){
            throw new ResourceAlreadyExistsException("this name -"+dto.name()+ "- already exists");
        }
        if (customerRepository.existsByEnabledIsTrueAndDni(dto.dni())){
            throw new ResourceAlreadyExistsException("this dni -"+dto.dni()+ "- already exists");
        }
        var customerCreated = CustomerMapper.toEntityCreated(dto);
        var customerSaved = customerRepository.save(customerCreated);
        return CustomerMapper.toCustomerCreated(customerSaved);
    }

    public CustomerFindOne findOne(Long id){
        var customer = customerRepository.findFirstByEnabledIsTrueAndIdCustomer(id)
                .orElseThrow(()-> new RuntimeException("customer doesnt exists"));
        return CustomerMapper.toCustomerFindOne(customer);
    }

    public CustomerUpdated update(CustomerUpdate dto, Long id){
        var customer = customerRepository.findFirstByEnabledIsTrueAndIdCustomer(id)
                .orElseThrow(()-> new RuntimeException("customer doesnt exists"));
        if (customerRepository.existsByEnabledIsTrueAndNameIgnoreCaseAndIdCustomerNot(dto.name(), id)){
            throw new ResourceAlreadyExistsException("this name -"+dto.name()+ "- already exists");
        }
        if (customerRepository.existsByEnabledIsTrueAndDniAndIdCustomerNot(dto.dni(), id)){
            throw new ResourceAlreadyExistsException("this dni -"+dto.dni()+ "- already exists");
        }
        var customerCreated = CustomerMapper.toEntityUpdated(dto,customer);
        var customerSaved = customerRepository.save(customerCreated);
        return CustomerMapper.toCustomerUpdated(customerSaved);
    }

    public void delete(Long id){
        var customer = customerRepository.findFirstByEnabledIsTrueAndIdCustomer(id)
                .orElseThrow(()-> new RuntimeException("customer doesnt exists"));
        customerRepository.save(CustomerMapper.toCustomerDeleted(customer));
    }


}
