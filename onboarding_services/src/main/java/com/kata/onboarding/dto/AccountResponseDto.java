package com.kata.onboarding.dto;

import com.kata.onboarding.domain.enums.AccountStatus;

public record AccountResponseDto(
        Long accountNumber,
        AccountStatus status
) {
}
