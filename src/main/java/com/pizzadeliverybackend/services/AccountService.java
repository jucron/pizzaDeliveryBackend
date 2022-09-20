package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.Account;
import com.pizzadeliverybackend.domain.Response;

public interface AccountService {
    void createAccount(Account account);

    Response processLogin(Account account);
}