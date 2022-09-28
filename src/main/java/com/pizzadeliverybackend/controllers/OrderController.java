package com.pizzadeliverybackend.controllers;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.EntityList;
import com.pizzadeliverybackend.domain.OrderHistory;
import com.pizzadeliverybackend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EntityList<ClientOrder> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("confirmed")
    @ResponseStatus(HttpStatus.OK)
    public EntityList<ClientOrder> getConfirmedOrders() {
        return orderService.getConfirmedOrders();
    }

    @GetMapping("accepted")
    @ResponseStatus(HttpStatus.OK)
    public EntityList<ClientOrder> getAcceptedOrders() {
        return orderService.getAcceptedOrders();
    }

    @GetMapping("finished")
    @ResponseStatus(HttpStatus.OK)
    public EntityList<ClientOrder> getFinishedOrders() {
        return orderService.getFinishedOrders();
    }

    @GetMapping("{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientOrder getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("{username}/username")
    @ResponseStatus(HttpStatus.OK)
    public ClientOrder getOrderByUsername(@PathVariable String username) {
        System.out.println("getOrderByUsername called with username: "+username);
        return orderService.getOrderByUsername(username);
    }

    @PutMapping ("{orderId}/{orderStatus}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeOrderStatus(
            @PathVariable String orderId, @PathVariable String orderStatus) {
        orderService.changeOrderStatus(orderId,orderStatus);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody ClientOrder order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping("{orderId}")
    @ResponseStatus(HttpStatus.FOUND)
    public void deactivateOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
    }

    @PatchMapping("{orderId}")
    @ResponseStatus(HttpStatus.FOUND)
    public void updateHistoryOrder(@PathVariable String orderId, @RequestBody OrderHistory orderHistory) {
        orderService.updateHistoryOrder(orderId, orderHistory);
    }
}
