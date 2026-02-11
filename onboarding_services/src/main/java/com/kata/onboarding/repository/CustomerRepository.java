package com.kata.onboarding.repository;

import com.kata.onboarding.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByDocumentNumber(String documentNumber);
}
