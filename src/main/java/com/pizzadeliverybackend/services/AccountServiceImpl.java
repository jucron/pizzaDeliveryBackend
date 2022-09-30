package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.Account;
import com.pizzadeliverybackend.model.Response;
import com.pizzadeliverybackend.repositories.AccountRepository;
import com.pizzadeliverybackend.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;
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
        Optional<Account> existentAccountOptional = accountRepository.findByUsername(account.getUsername());
        if (existentAccountOptional.isEmpty() || !Objects.equals(existentAccountOptional.get().getPassword(), account.getPassword())) {
            return null;} //return null if there is no match
        Account existentAccount = existentAccountOptional.get();
        existentAccount.setLoginStatus("logged");
        accountRepository.save(existentAccount);

        UUID orderIdAssociatedWithThisAccount = null;
        try {
            orderIdAssociatedWithThisAccount =
                    orderRepository.findAll().stream().filter(order ->
                                    Objects.equals(order.getAccount().getUsername(), account.getUsername()))
                            .collect(Collectors.toList()).get(0).getId();
            log.info("Order associated with Account: " + account.getUsername() +
                    " found with Id: " + orderIdAssociatedWithThisAccount);
        } catch (IndexOutOfBoundsException e) {
            log.info("no orders associated with this account were found");
        }

        return new Response()
                .withMessage(orderIdAssociatedWithThisAccount==null ? null : orderIdAssociatedWithThisAccount.toString());
    }

    @Override
    public Response processLogout(Account account) {
        Account existentAccount = accountRepository.findByUsername(account.getUsername()).get();
        existentAccount.setLoginStatus("not_logged");
        accountRepository.save(existentAccount);
        return new Response()
                .withMessage(account.getUsername());
    }
}

