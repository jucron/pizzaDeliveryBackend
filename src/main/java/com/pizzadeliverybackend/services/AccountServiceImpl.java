package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.controllers.FlowableController;
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
        log.info("createAccount executed");
        accountRepository.save(account);
    }

    @Override
    public Response processLogin(Account account) {
        log.info("processLogin executed");
        //todo: check login existence and return error if not found
        //todo: return username
        FlowableController.taskCount=0;
        return new Response()
                .withMessage(account.getUsername());
    }
}

