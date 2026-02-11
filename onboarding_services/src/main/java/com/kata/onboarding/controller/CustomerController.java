package com.kata.onboarding.controller;

import com.kata.onboarding.dto.CustomerRequestDto;
import com.kata.onboarding.dto.CustomerResponseDto;
import com.kata.onboarding.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Tag(name = "Get all customers")
    @Operation(
            summary = "Get customers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List with all customers")
            }
    )
    @GetMapping()
    public ResponseEntity<List<CustomerResponseDto>> getAll(){
        return ResponseEntity.ok().body(this.customerService.getAll());
    }

    @Tag( name = "Create a customer")
    @Operation(
            summary = "Add a new customer!",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Customer created"),
                    @ApiResponse(responseCode = "400", description = "Bad request. Please validate your input and try again.")
            }
    )
    @PostMapping()
    public ResponseEntity<CustomerResponseDto> save(@Valid @RequestBody CustomerRequestDto customerRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.customerService.save(customerRequest));
    }

}
