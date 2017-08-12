package com.jooble.testrail.service;

import com.jooble.testrail.entity.User;

import java.math.BigDecimal;

public interface BankService {
    boolean payment(User user, BigDecimal value);
}
