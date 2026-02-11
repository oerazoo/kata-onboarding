package com.kata.onboarding.service;

import com.kata.onboarding.domain.Account;
import com.kata.onboarding.domain.Customer;
import com.kata.onboarding.domain.enums.AccountStatus;
import com.kata.onboarding.dto.AccountResponseDto;
import com.kata.onboarding.exception.CustomerHasAccountException;
import com.kata.onboarding.exception.NotFoundException;
import com.kata.onboarding.mapper.AccountMapper;
import com.kata.onboarding.repository.AccountRepository;
import com.kata.onboarding.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    public List<AccountResponseDto> getAll( Long customerId ){

        if(customerId != null){
            //VALIDATE IF CUSTOMER EXISTS
            this.validateCustomer(customerId);

            return this.accountMapper.toResponse(this.accountRepository.findByCustomerId(customerId));
        }

        return accountRepository.findAll().stream().map(accountMapper::toResponse).toList();
    }

    public AccountResponseDto save(Long customerId){

        //VALIDATE IF CUSTOMER EXISTS
        this.validateCustomer(customerId);

        //VALIDATE IF CUSTOMER HAS ACCOUNTS
        List<Account> customerAccounts = this.accountRepository.findByCustomerId(customerId);

        if(!customerAccounts.isEmpty()){
            throw new CustomerHasAccountException();
        }

        Account newAccount = new Account();
        newAccount.setAccountNumber(this.generateAccountNumber());
        newAccount.setCustomerId(customerId);
        newAccount.setStatus(AccountStatus.ACTIVE);

        return this.accountMapper.toResponse(this.accountRepository.save(newAccount));
    }

    private Long generateAccountNumber(){
        return (long) (Math.random() * 1000000000L);
    }

    private void validateCustomer(Long customerId){
        this.customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("customer", customerId.toString()));
    }
}
