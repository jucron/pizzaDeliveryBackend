package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.Account;

public interface AccountService {
    void createAccount(Account account);

    String processLogin(Account account);
}
