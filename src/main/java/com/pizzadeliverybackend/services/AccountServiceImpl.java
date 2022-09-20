package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.Account;
import com.pizzadeliverybackend.domain.Response;
import com.pizzadeliverybackend.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Response processLogin(Account account) {
        log.info("processLogin executed");
        //check login existence
        //throw back process pending tasks
        return new Response()
                .withMessage("task_1");
    }
}

