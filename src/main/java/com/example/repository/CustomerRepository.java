package com.example.repository;

import com.example.entity.CustomerEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findAllByEnabledIsTrueOrderByIdCustomerDesc();

    Optional<CustomerEntity> findFirstByEnabledIsTrueAndIdCustomer(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndNameIgnoreCaseAndIdCustomerNot(String name, Long id);

    boolean existsByEnabledIsTrueAndDni(String dni);

    boolean existsByEnabledIsTrueAndDniAndIdCustomerNot(String dni, Long id);
}
