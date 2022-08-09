package com.pizzadeliverybackend.controllers;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.EntityList;
import com.pizzadeliverybackend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EntityList<ClientOrder> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("active")
    @ResponseStatus(HttpStatus.OK)
    public EntityList<ClientOrder> getActiveAllOrders() {
        return orderService.getActiveOrders();
    }

    @PutMapping ("{orderId}/{orderStatus}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeOrderStatus(
            @PathVariable Long orderId, @PathVariable String orderStatus) {
        orderService.changeOrderStatus(orderId,orderStatus);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody ClientOrder order) {
        orderService.createOrder(order);
    }

    @DeleteMapping("{orderId}")
    @ResponseStatus(HttpStatus.FOUND)
    public void deactivateOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
}
