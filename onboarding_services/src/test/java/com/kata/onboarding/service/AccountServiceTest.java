package com.kata.onboarding.service;

import com.kata.onboarding.domain.Account;
import com.kata.onboarding.exception.NotFoundException;
import com.kata.onboarding.repository.AccountRepository;
import com.kata.onboarding.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private  AccountService accountService;

    @Test
    @DisplayName("Should throw an exception if client not exists")
    void shouldThrowExceptionWhenCustomerNotFound(){
        //Preparation
        Long customerId = 24L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            accountService.save(customerId);
        });

        verify(accountRepository, never()).save((any()));
    }

}
