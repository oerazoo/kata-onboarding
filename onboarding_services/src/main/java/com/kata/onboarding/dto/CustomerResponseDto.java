package com.kata.onboarding.dto;

public record CustomerResponseDto(
        Long id,
        String email,
        String documentNumber,
        String fullName
) {
}
