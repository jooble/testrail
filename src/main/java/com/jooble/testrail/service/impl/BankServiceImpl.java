package com.jooble.testrail.service.impl;

import com.jooble.testrail.entity.User;
import com.jooble.testrail.service.BankService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankServiceImpl implements BankService {

    @Override
    public boolean payment(User user, BigDecimal value) {
        return true;
    }
}
