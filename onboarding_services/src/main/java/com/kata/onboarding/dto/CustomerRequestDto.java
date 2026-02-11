package com.kata.onboarding.dto;

import com.kata.onboarding.domain.enums.DocumentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDto(

        @NotNull(message = "Document type is mandatory")
        DocumentType documentType,

        @NotBlank(message = "Document number is mandatory")
        String documentNumber,

        @NotBlank(message = "Name is mandatory")
        String fullName,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email format is not valid")
        String email
) {
}
