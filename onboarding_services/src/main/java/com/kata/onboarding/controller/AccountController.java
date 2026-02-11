package com.kata.onboarding.controller;

import com.kata.onboarding.dto.AccountRequestDto;
import com.kata.onboarding.dto.AccountResponseDto;
import com.kata.onboarding.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Tag(name = "Get all accounts")
    @Operation(summary = "All accounts list",
        responses = {
            @ApiResponse(responseCode = "200", description = "accounts list")
        }
    )
    @GetMapping()
    public ResponseEntity<List<AccountResponseDto>> getAll(@Parameter(name = "customerId", required = false, description = "The customer id to get all their accounts") @RequestParam(required = false) Long customerId){
        return ResponseEntity.ok().body(this.accountService.getAll(customerId));
    }

    @Tag(name = "Create an account")
    @Operation(summary = "Add a new basic account",
            responses = {
                    @ApiResponse(responseCode = "201", description = "account created"),
                    @ApiResponse(responseCode = "400", description = "Arguments are not enough"),
                    @ApiResponse(responseCode = "404", description = "Client not found")
            }
    )
    @PostMapping()
    public ResponseEntity<AccountResponseDto> save(@RequestBody AccountRequestDto accountRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.accountService.save(accountRequest.customerId()));
    }
}
