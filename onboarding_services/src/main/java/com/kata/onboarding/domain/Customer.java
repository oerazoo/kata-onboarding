package com.kata.onboarding.domain;

import com.kata.onboarding.domain.enums.DocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private DocumentType documentType;

    @Column(nullable = false, unique = true)
    private String documentNumber;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;
}
