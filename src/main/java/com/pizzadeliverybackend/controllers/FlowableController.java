package com.pizzadeliverybackend.controllers;

import com.pizzadeliverybackend.domain.Account;
import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.model.Response;
import com.pizzadeliverybackend.repositories.AccountRepository;
import com.pizzadeliverybackend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flowable/client/")
@RequiredArgsConstructor
@CrossOrigin
public class FlowableController {
    private final OrderService orderService;
    private final AccountRepository accountRepository;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Response createProcess(@RequestBody ClientOrder order) {
        //todo: Create Flowable Process

        ClientOrder orderSaved = orderService.createOrder(order);

        return new Response()
                .withMessage(orderSaved.getId().toString());
    }

    @GetMapping("{username}")
    @ResponseStatus(HttpStatus.OK)
    public Response getClientStatus(@PathVariable String username) {
        //todo: find Flowable process by username:
        Account account = accountRepository.findByUsername(username).get();
        System.out.println("ACCOUNT FOUND: "+account);
        return new Response()
                .withMessage(account.getLoginStatus())
                .withMessageB(account.getTaskStatus());
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void changeClientTaskStatus(@RequestBody Account account) {
        //todo: Complete Task in Flowable Process
        Account existingAccount = accountRepository.findByUsername(account.getUsername()).get();
        existingAccount.setTaskStatus(account.getTaskStatus());
        accountRepository.save(existingAccount);
    }

}
