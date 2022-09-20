package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.Account;
import com.pizzadeliverybackend.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public String processLogin(Account account) {
        //check login existence
        //throw back process pending tasks
        return "task_1";
    }
}
