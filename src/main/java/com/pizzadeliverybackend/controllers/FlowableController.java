package com.pizzadeliverybackend.controllers;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.Response;
import com.pizzadeliverybackend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flowable/")
@RequiredArgsConstructor
@CrossOrigin
public class FlowableController {
    private final OrderService orderService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Response createAccount(@RequestBody ClientOrder order) {
        return new Response()
                .withMessage(orderService.createOrder(order));
    }

}
