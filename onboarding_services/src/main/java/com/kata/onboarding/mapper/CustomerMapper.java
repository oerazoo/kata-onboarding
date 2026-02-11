package com.kata.onboarding.mapper;

import com.kata.onboarding.domain.Customer;
import com.kata.onboarding.dto.CustomerRequestDto;
import com.kata.onboarding.dto.CustomerResponseDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    @Mapping(source = "documentNumber", target = "documentNumber")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "id", target = "id")
    CustomerResponseDto customerToResponse(Customer customer);
    List<CustomerResponseDto> customerToResponse(Iterable<Customer> customers);

    @InheritInverseConfiguration
    Customer toEntity(CustomerRequestDto dto);

}
