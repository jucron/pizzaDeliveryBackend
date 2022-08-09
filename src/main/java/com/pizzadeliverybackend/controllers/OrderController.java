package com.pizzadeliverybackend.controllers;

import com.pizzadeliverybackend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
}
