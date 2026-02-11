package com.kata.onboarding.service;

import com.kata.onboarding.domain.Customer;
import com.kata.onboarding.dto.CustomerRequestDto;
import com.kata.onboarding.dto.CustomerResponseDto;
import com.kata.onboarding.exception.ResourceAlreadyExistsException;
import com.kata.onboarding.mapper.CustomerMapper;
import com.kata.onboarding.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerResponseDto> getAll(){
        return this.customerMapper.customerToResponse(this.customerRepository.findAll());
    }

    public CustomerResponseDto save(CustomerRequestDto customerRequest){

        boolean customerExists = this.customerRepository.existsByDocumentNumber(customerRequest.documentNumber());

        if(customerExists){
            throw new ResourceAlreadyExistsException("customer", customerRequest.documentNumber().toString());
        }

        Customer newCustomer = this.customerRepository.save(this.customerMapper.toEntity(customerRequest));

        return this.customerMapper.customerToResponse(newCustomer);
    }

}
