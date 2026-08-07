package com.kata.onboarding.util;

import org.junit.jupiter.api.Test;

import static com.kata.onboarding.util.PasswordUtil.SecurityLevel.*;
import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {

    @Test
    public void weak_when_less_than_8_chars(){
        assertEquals(WEAK, PasswordUtil.assessPassword("23454"));
    }

    @Test
    public void weak_when_has_only_letters(){
        assertEquals(WEAK, PasswordUtil.assessPassword("asdgag"));
    }

    @Test
    public void medium_when_has_letters_and_numbers(){
        assertEquals(MEDIUM, PasswordUtil.assessPassword("1234eSSse"));
    }

    @Test
    public void strong_when_has_letters_and_numbers_and_symbols(){
        assertEquals(STRONG, PasswordUtil.assessPassword("1234eSSse!!!"));
    }




}