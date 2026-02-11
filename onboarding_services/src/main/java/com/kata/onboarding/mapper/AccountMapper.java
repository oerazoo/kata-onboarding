package com.kata.onboarding.mapper;


import com.kata.onboarding.domain.Account;
import com.kata.onboarding.dto.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {


    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "status", target = "status")
    AccountResponseDto toResponse(Account account);
    List<AccountResponseDto> toResponse(Iterable<Account> accounts);

}
