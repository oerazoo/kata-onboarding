package com.kata.onboarding.exception;

public class CustomerHasAccountException extends RuntimeException {
    public CustomerHasAccountException() {
        super("Customer is not allowed to have more than 1 account");
    }
}
