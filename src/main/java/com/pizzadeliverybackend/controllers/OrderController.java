package com.pizzadeliverybackend.controllers;

import com.pizzadeliverybackend.domain.EntityList;
import com.pizzadeliverybackend.domain.Order;
import com.pizzadeliverybackend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EntityList<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("active")
    @ResponseStatus(HttpStatus.OK)
    public EntityList<Order> getActiveAllOrders() {
        return orderService.getActiveOrders();
    }

    @PutMapping ("{orderId}/{orderStatus}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeOrderStatus(
            @PathVariable String orderId, @PathVariable String orderStatus) {
        orderService.changeOrderStatus(orderId,orderStatus);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
    }

    @DeleteMapping("{orderId}")
    @ResponseStatus(HttpStatus.FOUND)
    public void deactivateOrder(@PathVariable String orderId) {
        orderService.deactivateOrder(orderId);
    }
}
